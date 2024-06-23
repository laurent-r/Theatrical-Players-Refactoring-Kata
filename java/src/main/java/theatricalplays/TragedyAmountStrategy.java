package theatricalplays;

class TragedyAmountStrategy extends AmountStrategy {

    @Override
    protected int baseAmount(int audience) {
        return 40000;
    }

    @Override
    protected int audienceThreshold() {
        return 30;
    }

    @Override
    protected int extraAmount(int audience) {
        return 1000 * (audience - audienceThreshold());
    }
}
