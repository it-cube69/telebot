package ru.itcube.telebot.domain;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Bucket {
    private final Person person;
    private List<Product> products = new CopyOnWriteArrayList<>();

    public Bucket(Person person) {
        this.person = person;
        this.person.setBucket(this);
    }

    public Person getPerson() {
        return person;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
}
