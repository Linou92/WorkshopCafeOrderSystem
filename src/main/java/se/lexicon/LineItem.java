package se.lexicon;

public class LineItem {

    private MenuItem item;
    private int quantity;

    public LineItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double lineTotal() {
        return item.getPrice() * quantity;
    }
}
