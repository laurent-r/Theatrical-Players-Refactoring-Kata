package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    private StatementPrinter() {}

    public static String statement(Invoice invoice, Map<String, Play> plays) {
        return renderPlainText(new StatementData(invoice, plays));
    }

    static String renderPlainText(StatementData statementData) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Statement for %s%n", statementData.customer));
        for (var perf : statementData.performances) {
            sb.append(String.format("  %s: %s (%s seats)%n", perf.playName, usdFormat(perf.amount / 100), perf.audience));
        }
        sb.append(String.format("Amount owed is %s%n", usdFormat(statementData.totalAmount / 100)));
        sb.append(String.format("You earned %s credits%n", statementData.totalVolumeCredits));
        return sb.toString();
    }

    public static String htmlStatement(Invoice invoice, Map<String, Play> plays) {
        return renderHtml(new StatementData(invoice, plays));
    }

    static String renderHtml(StatementData statementData) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("<h1>Statement for %s</h1>%n", statementData.customer));
        sb.append("<table>\n<tr><th>play</th><th>seats</th><th>cost</th></tr>\n");
        for (var perf : statementData.performances) {
            sb.append(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>%n", perf.playName, perf.audience, usdFormat(perf.amount / 100)));
        }
        sb.append("</table>\n");
        sb.append(String.format("<p>Amount owed is <em>%s</em></p>%n", usdFormat(statementData.totalAmount / 100)));
        sb.append(String.format("<p>You earned <em>%s</em> credits</p>", statementData.totalVolumeCredits));
        return sb.toString();
    }

    private static String usdFormat(int number) {
        return NumberFormat.getCurrencyInstance(Locale.US).format(number);
    }

}
