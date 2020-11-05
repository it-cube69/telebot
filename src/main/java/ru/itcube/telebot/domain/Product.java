package ru.itcube.telebot.domain;

public class Product {
    private final String title;
    private final Double price;

    public Product(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Название: %s, цена: %.2f", title, price);
    }
}
