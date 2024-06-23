package theatricalplays;

class CreditsStrategy {

    int calculate(int audience) {
        return Math.max(audience - 30, 0);
    }
}
