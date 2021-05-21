package datatypes;

public class Version {
    private String major;
    private String minor;
    private String patch;

    public Version(String major, String minor, String patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public String getMajor() {
        return major;
    }

    public String getMinor() {
        return minor;
    }

    public String getPatch() {
        return patch;
    }
}

