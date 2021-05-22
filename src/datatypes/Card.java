package datatypes;

public class Card {

    private String name;
    private int id;

    public Card(String name, int cardNameIndex) {
        this.name = name;
        this.id = cardNameIndex+1;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}' + "\n";
    }
}
