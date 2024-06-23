package theatricalplays;

class CreditsStrategy {

    int calculate(int audience) {
        return baseCredits(audience) + extraCredits(audience);
    }

    protected int baseCredits(int audience) {
        return Math.max(audience - 30, 0);
    }

    protected int extraCredits(int audience) {
        return 0;
    }

}
