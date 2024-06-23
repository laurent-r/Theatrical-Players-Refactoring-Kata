package theatricalplays;

class ComedyAmountStrategy implements AmountStrategy {

    @Override
    public int calculate(int audience) {
        int amount = 30000 + 300 * audience;
        if (audience > 20) {
            amount += 10000 + 500 * (audience - 20);
        }
        return amount;
    }
}
