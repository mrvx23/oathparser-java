package datatypes;

import java.util.List;

public class Site {

    private String name;
    private boolean ruined;
    private List<Card> cards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRuined() {
        return ruined;
    }

    public void setRuined(boolean ruined) {
        this.ruined = ruined;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public String toString() {
        return "Site{" +
                "name='" + name + '\'' +
                ", ruined=" + ruined +
                ", cards=" + cards +
                '}';
    }
}
