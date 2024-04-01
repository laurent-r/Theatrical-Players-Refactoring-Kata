package theatricalplays;

import java.util.List;
import java.util.Map;

import static theatricalplays.Play.Type.COMEDY;

public class StatementData {

    final String customer;
    final List<PerformanceData> performances;
    final int totalAmount;
    final int totalVolumeCredits;

    StatementData(Invoice invoice, Map<String, Play> plays) {
        customer = invoice.customer();
        performances = invoice.performances().stream()
                .map(perf -> new PerformanceData(plays.get(perf.playID()), perf.audience()))
                .toList();
        totalAmount = performances.stream().mapToInt(p -> p.amount).sum();
        totalVolumeCredits = performances.stream().mapToInt(p -> p.volumeCredits).sum();
    }

    static class PerformanceData {
        final String playName;
        final Play.Type playType;
        final int audience;
        final int amount;
        final int volumeCredits;

        PerformanceData(Play play, int audience) {
            this.playName = play.name();
            this.playType = play.type();
            this.audience = audience;
            this.amount = calculateAmount();
            this.volumeCredits = calculateVolumeCredits();
        }

        private int calculateAmount() {
            int amount;
            switch (playType) {
                case TRAGEDY:
                    amount = 40000;
                    if (audience > 30) {
                        amount += 1000 * (audience - 30);
                    }
                    return amount;
                case COMEDY:
                    amount = 30000;
                    if (audience > 20) {
                        amount += 10000 + 500 * (audience - 20);
                    }
                    amount += 300 * audience;
                    return amount;
                default:
                    throw new IllegalArgumentException("unknown type: " + playType);
            }
        }

        private int calculateVolumeCredits() {
            int volumeCredits = Math.max(audience - 30, 0);
            // add extra credit for every ten comedy attendees
            if (playType==COMEDY) volumeCredits += audience / 5;
            return volumeCredits;
        }

    }

}
