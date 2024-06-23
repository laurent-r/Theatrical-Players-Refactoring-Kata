package theatricalplays;

class ComedyCreditsStrategy extends CreditsStrategy {

    @Override
    protected int extraCredits(int audience) {
        // add extra credit for every five comedy attendees
        return audience / 5;
    }
}
