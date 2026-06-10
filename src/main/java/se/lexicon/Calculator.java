package se.lexicon;

public class Calculator {

    public double subtotal(Order order) {
        return order.getItem().getPrice() * order.getQuantity();
    }

    public double discount(double subtotal, boolean member) {

        if (member) return subtotal * 0.15;

        if (subtotal > 150) return subtotal * 0.10;

        return 0;
    }

    public double vat(double afterDiscount) {
        return afterDiscount * 0.12;
    }
}
