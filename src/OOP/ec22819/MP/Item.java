package OOP.ec22819.MP;//import java.util.List;

class Item {

    final String name;

    Item(String nameOfItem) {
        name = nameOfItem;
    }

    public boolean equals(Item x) {
        return name.equals(x.name);
    }

    public String toString() {
        return name + "(" + this.hashCode() + ")";
    }
}