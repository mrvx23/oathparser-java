package enums;

public enum Suit {
    Discord(0),
    Hearth(1),
    Nomad(2),
    Arcane(3),
    Order(4),
    Beast(5);

    private int code;

    Suit(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
