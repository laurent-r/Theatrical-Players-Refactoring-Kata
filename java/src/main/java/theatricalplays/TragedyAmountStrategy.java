package theatricalplays;

class TragedyAmountStrategy implements AmountStrategy {

    @Override
    public int calculate(int audience) {
        int amount = 40000;
        if (audience > 30) {
            amount += 1000 * (audience - 30);
        }
        return amount;
    }
}
