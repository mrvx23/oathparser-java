package datatypes;

import enums.Citizenship;
import enums.PlayerColor;
import enums.Suit;

import java.util.List;
import java.util.Map;

public class OathGame {

    private Version version;

    private int gameCount;
    private String chronicleName;

    private Map<PlayerColor, Citizenship> playerCitizenship;
    private String oath;
    private List<Suit> suitOrder;
    private List<Site> sites;
    private List<Card> world;
    private List<Card> dispossessed;
    private List<Card> relics;

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public int getGameCount() {
        return gameCount;
    }

    public void setGameCount(int gameCount) {
        this.gameCount = gameCount;
    }

    public String getChronicleName() {
        return chronicleName;
    }

    public void setChronicleName(String chronicleName) {
        this.chronicleName = chronicleName;
    }

    public Map<PlayerColor, Citizenship> getPlayerCitizenship() {
        return playerCitizenship;
    }

    public void setPlayerCitizenship(Map<PlayerColor, Citizenship> playerCitizenship) {
        this.playerCitizenship = playerCitizenship;
    }

    public String getOath() {
        return oath;
    }

    public void setOath(String oath) {
        this.oath = oath;
    }

    public List<Suit> getSuitOrder() {
        return suitOrder;
    }

    public void setSuitOrder(List<Suit> suitOrder) {
        this.suitOrder = suitOrder;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    public List<Card> getWorld() {
        return world;
    }

    public void setWorld(List<Card> world) {
        this.world = world;
    }

    public List<Card> getDispossessed() {
        return dispossessed;
    }

    public void setDispossessed(List<Card> dispossessed) {
        this.dispossessed = dispossessed;
    }

    public List<Card> getRelics() {
        return relics;
    }

    public void setRelics(List<Card> relics) {
        this.relics = relics;
    }


    @Override
    public String toString() {
        return "OathGame{" +
                "version=" + version +
                ", gameCount=" + gameCount +
                ", chronicleName='" + chronicleName + '\'' +
                ", playerCitizenship=" + playerCitizenship +
                ", oath='" + oath + '\'' +
                ", suitOrder=" + suitOrder +
                ", sites=" + sites +
                ", world=" + world +
                ", dispossessed=" + dispossessed +
                ", relics=" + relics +
                '}';
    }
}
