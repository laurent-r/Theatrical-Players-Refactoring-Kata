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

    static class Comedy extends AmountStrategy {

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

    static class Tragedy extends AmountStrategy {

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
}
