package theatricalplays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;

class StatementPrinterTests {

    private Map<String, Play> plays;
    private Invoice invoice;

    @BeforeEach
    public void setup() {
        plays = Map.of(
                "hamlet",  new Play("Hamlet", "tragedy"),
                "as-like", new Play("As You Like It", "comedy"),
                "othello", new Play("Othello", "tragedy"));

        invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));
    }

    @Test
    void exampleStatement() {
        var result = StatementPrinter.statement(invoice, plays);

        verify(result);
    }

    @Test
    void exampleHtmlStatement() {
        var result = StatementPrinter.htmlStatement(invoice, plays);

        verify(result);
    }

    @Test
    void statementWithNewPlayTypes() {
        plays = Map.of(
                "henry-v",  new Play("Henry V", "history"),
                "as-like", new Play("As You Like It", "pastoral"));

        invoice = new Invoice("BigCo", List.of(
                new Performance("henry-v", 53),
                new Performance("as-like", 55)));

        Assertions.assertThrows(IllegalArgumentException.class, () -> new StatementData(invoice, plays));
    }
}
