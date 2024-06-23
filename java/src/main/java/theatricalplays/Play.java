package theatricalplays;

public record Play(String name, Type type) {

    public static Play create(String name, String type) {
        return new Play(name, Type.valueOf(type.toUpperCase()));
    }
}

    enum Type {
        COMEDY(new ComedyAmountStrategy(), new ComedyCreditsStrategy()),
        TRAGEDY(new TragedyAmountStrategy(), new CreditsStrategy());

        private final AmountStrategy amountStrategy;
        private final CreditsStrategy creditsStrategy;

        Type(AmountStrategy amountStrategy, CreditsStrategy creditsStrategy) {
            this.amountStrategy = amountStrategy;
            this.creditsStrategy = creditsStrategy;
        }

        int calculateAmount(int audience) {
            return amountStrategy.calculate(audience);
        }

        int calculateVolumeCredits(int audience) {
            return creditsStrategy.calculate(audience);
        }
    }

