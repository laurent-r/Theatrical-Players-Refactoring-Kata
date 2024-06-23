package theatricalplays;

class AmountStrategy {

    int calculate(int audience) {
        int amount = baseAmount(audience);
        if (audience > audienceThreshold()) {
            amount += extraAmount(audience);
        }
        return amount;
    }

    protected int baseAmount(int audience) {
        return 0;
    }

    protected int audienceThreshold() {
        return 0;
    }

    protected int extraAmount(int audience) {
        return 0;
    }
}
