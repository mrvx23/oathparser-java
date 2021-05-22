package datatypes;

import java.util.List;

public class Site {

    private String name;
    private boolean facedown;
    private List<Card> cards;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFacedown() {
        return facedown;
    }

    public void setFacedown(boolean facedown) {
        this.facedown = facedown;
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
                ", facedown=" + facedown +
                ", cards=" + cards +
                '}' + "\n";
    }
}
