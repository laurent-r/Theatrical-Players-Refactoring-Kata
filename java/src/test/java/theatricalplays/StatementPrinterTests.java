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
                "hamlet",  Play.create("Hamlet", "tragedy"),
                "as-like", Play.create("As You Like It", "comedy"),
                "othello", Play.create("Othello", "tragedy"));

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
    void unknownPlayType() {
        Assertions.assertThrows(Exception.class, () -> Play.create("As You Like It", "pastoral"));
    }
}
