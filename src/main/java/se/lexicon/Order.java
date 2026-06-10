package se.lexicon;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private Customer customer;
    private List<LineItem> items;

    public Order(Customer customer) {
        this.customer = customer;
        this.items  = new ArrayList<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addItem(LineItem item) {
        items.add(item);
    }

    public List<LineItem> getItems() {
        return items;
    }
}
