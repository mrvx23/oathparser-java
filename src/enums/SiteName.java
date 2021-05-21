package enums;

public enum SiteName {
    MINE("Mine", 0),
    SALT_FLATS("Salt Flats", 1),
    FERTILE_VALLEY("Fertile Valley", 2),
    BARREN_COAST("Barren Coast", 3),
    PLAINS("Plains", 4),
    RIVER("River", 5),
    STEPPE("Steppe", 6),
    MOUNTAIN("Mountain", 7),
    LUSH_COAST("Lush Coast", 8),
    MARSHES("Marshes", 9),
    WASTES("Wastes", 10),
    ROCKY_COAST("Rocky Coast", 11),
    NARROW_PASS("Narrow Pass", 12),
    CHARMING_VALLEY("Charming Valley", 13),
    DEEP_WOODS("Deep Woods", 14),
    STANDING_STONES("Standing Stones", 15),
    ANCIENT_CITY("Ancient City", 16),
    DROWNED_CITY("Drowned City", 17),
    GREAT_SLUM("Great Slum", 18),
    BURIED_GIANT("Buried Giant", 19),
    TRIBUNAL("The Tribunal", 20),
    SHROUDED_WOOD("Shrouded Wood", 21),
    HIDDEN_PLACE("The Hidden Place", 22),

    NONE("NONE", 255);

    private String name;
    private int code;

    SiteName(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public static SiteName getByCode(int code) {
        for (SiteName value : values())
            if (value.getCode() == code || value.getCode()+24 == code)
                return value;

        return null;
    }
}
