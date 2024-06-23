package theatricalplays;

class ComedyCreditsStrategy extends CreditsStrategy {

    @Override
    public int calculate(int audience) {
        int volumeCredits = super.calculate(audience);
        // add extra credit for every five comedy attendees
        volumeCredits += audience / 5;
        return volumeCredits;
    }
}
