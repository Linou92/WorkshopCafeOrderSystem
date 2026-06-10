package se.lexicon;

public class Customer {

    private String name;
    private boolean member;

    public Customer(String name, boolean member) {
        this.name = name;
        this.member = member;
    }

    public String getName() {
        return name;
    }

    public boolean isMember() {
        return member;
    }
}