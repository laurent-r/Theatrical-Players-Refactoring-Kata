package theatricalplays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;
import static theatricalplays.Play.Type.*;

class StatementPrinterTests {

    private Map<String, Play> plays;
    private Invoice invoice;

    @BeforeEach
    public void setup() {
        plays = Map.of(
                "hamlet",  new Play("Hamlet", TRAGEDY),
                "as-like", new Play("As You Like It", COMEDY),
                "othello", new Play("Othello", TRAGEDY));

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
                "hamlet",  new Play("Hamlet", TRAGEDY),
                "as-like", new Play("As You Like It", WESTERN));

        invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 53),
                new Performance("as-like", 55)));

        Assertions.assertThrows(Exception.class, () -> new StatementData(invoice, plays));
    }
}
