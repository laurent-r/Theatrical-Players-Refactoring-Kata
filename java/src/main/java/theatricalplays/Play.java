package theatricalplays;

public record Play (String name, Type type) {
    enum Type { TRAGEDY, COMEDY }
}
