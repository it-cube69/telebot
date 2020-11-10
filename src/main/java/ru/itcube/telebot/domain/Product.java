package ru.itcube.telebot.domain;

public class Product {
    private final String title;
    private final Double price;
    private final Long id;

    public Product(Long id, String title, Double price) {
        this.title = title;
        this.price = price;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("Идентификатор: %d, Название: %s, цена: %.2f", id, title, price);
    }
}
