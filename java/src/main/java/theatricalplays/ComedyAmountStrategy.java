package theatricalplays;

class ComedyAmountStrategy extends AmountStrategy {

    @Override
    protected int baseAmount(int audience) {
        return 30000 + 300 * audience;
    }

    @Override
    protected int audienceThreshold() {
        return 20;
    }

    @Override
    protected int extraAmount(int audience) {
        return 10000 + 500 * (audience - audienceThreshold());
    }
}
