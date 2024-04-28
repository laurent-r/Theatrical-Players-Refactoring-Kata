package theatricalplays;

import java.util.List;
import java.util.Map;

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
        final int audience;
        final int amount;
        final int volumeCredits;

        PerformanceData(Play play, int audience) {
            this.playName = play.name();
            this.audience = audience;
            this.amount = play.type().calculateAmount(audience);
            this.volumeCredits = play.type().calculateVolumeCredits(audience);
        }

    }

}
