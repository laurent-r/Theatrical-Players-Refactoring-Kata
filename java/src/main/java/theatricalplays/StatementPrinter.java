package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    private final Invoice invoice;
    private final Map<String, Play> plays;

    StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public static String print(Invoice invoice, Map<String, Play> plays) {
        StatementPrinter printer = new StatementPrinter(invoice, plays);
        return printer.print();
    }

    String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Statement for %s%n", invoice.customer));
        for (var perf : invoice.performances) {
            sb.append(String.format("  %s: %s (%s seats)%n", playFor(perf).name, usdFormat(amountFor(perf) / 100), perf.audience));
        }
        sb.append(String.format("Amount owed is %s%n", usdFormat(totalAmount() / 100)));
        sb.append(String.format("You earned %s credits%n", totalVolumeCredits()));
        return sb.toString();
    }

    private String usdFormat(int number) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(number);
    }

    private Play playFor(Performance perf) {
        return plays.get(perf.playID);
    }

    private int totalAmount() {
        var result = 0;
        for (var perf : invoice.performances) {
            result += amountFor(perf);
        }
        return result;
    }

    private int amountFor(Performance perf) {
        int result;
        String type = playFor(perf).type;
        switch (type) {
            case "tragedy":
                result = 40000;
                if (perf.audience > 30) {
                    result += 1000 * (perf.audience - 30);
                }
                break;
            case "comedy":
                result = 30000;
                if (perf.audience > 20) {
                    result += 10000 + 500 * (perf.audience - 20);
                }
                result += 300 * perf.audience;
                break;
            default:
                throw new Error("unknown type: ${type}");
        }
        return result;
    }

    private int totalVolumeCredits() {
        var result = 0;
        for (var perf : invoice.performances) {
            result += volumeCreditsFor(perf);
        }
        return result;
    }

    private int volumeCreditsFor(Performance perf) {
        int result = Math.max(perf.audience - 30, 0);
        // add extra credit for every ten comedy attendees
        if ("comedy".equals(playFor(perf).type)) result += perf.audience / 5;
        return result;
    }

}
