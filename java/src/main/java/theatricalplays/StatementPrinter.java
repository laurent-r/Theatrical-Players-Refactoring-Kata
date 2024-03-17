package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public static String print(Invoice invoice, Map<String, Play> plays) {
        return print(new StatementData(invoice, plays));
    }

    static String print(StatementData statementData) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Statement for %s%n", statementData.customer));
        for (var perf : statementData.performances) {
            sb.append(String.format("  %s: %s (%s seats)%n", perf.name, usdFormat(perf.amount / 100), perf.audience));
        }
        sb.append(String.format("Amount owed is %s%n", usdFormat(statementData.totalAmount / 100)));
        sb.append(String.format("You earned %s credits%n", statementData.totalVolumeCredits));
        return sb.toString();
    }

    private static String usdFormat(int number) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(number);
    }

}
