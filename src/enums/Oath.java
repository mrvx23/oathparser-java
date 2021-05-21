package enums;

public enum Oath {
    Supremacy(0),
    People(1),
    Devotion(2),
    Protection(3),
    Conspiracy(4);

    private int code;

    Oath(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static String getNameByCode(int code) {
        for (Oath value : values())
            if (value.getCode() == code)
                return value.name();

        return null;
    }
}
