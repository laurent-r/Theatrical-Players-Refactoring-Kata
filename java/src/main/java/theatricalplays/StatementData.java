package theatricalplays;

import java.util.List;
import java.util.Map;

public class StatementData {

    final String customer;
    final List<PerformanceData> performances;
    final int totalAmount;
    final int totalVolumeCredits;

    StatementData(Invoice invoice, Map<String, Play> plays) {
        customer = invoice.customer;
        performances = invoice.performances.stream()
                .map(perf -> new PerformanceData(plays.get(perf.playID), perf.audience))
                .toList();
        totalAmount = performances.stream().mapToInt(p -> p.amount).sum();
        totalVolumeCredits = performances.stream().mapToInt(p -> p.volumeCredits).sum();
    }

    static class PerformanceData {
        final String name;
        final String type;
        final int audience;
        final int amount;
        final int volumeCredits;

        PerformanceData(Play play, int audience) {
            this.name = play.name;
            this.type = play.type;
            this.audience = audience;
            this.amount = evalAmount();
            this.volumeCredits = evalVolumeCredits();
        }

        private int evalAmount() {
            int result;
            switch (type) {
                case "tragedy":
                    result = 40000;
                    if (audience > 30) {
                        result += 1000 * (audience - 30);
                    }
                    break;
                case "comedy":
                    result = 30000;
                    if (audience > 20) {
                        result += 10000 + 500 * (audience - 20);
                    }
                    result += 300 * audience;
                    break;
                default:
                    throw new Error("unknown type: ${type}");
            }
            return result;
        }

        private int evalVolumeCredits() {
            int result = Math.max(audience - 30, 0);
            // add extra credit for every ten comedy attendees
            if ("comedy".equals(type)) result += audience / 5;
            return result;
        }

    }

}
