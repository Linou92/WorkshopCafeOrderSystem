package se.lexicon;

public class Order {

    private Customer customer;
    private MenuItem item;
    private int quantity;

    public Order(Customer customer, MenuItem item, int quantity) {
        this.customer = customer;
        this.item = item;
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
