package ru.itcube.telebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.itcube.telebot.domain.Person;
import ru.itcube.telebot.domain.Product;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@SpringBootApplication
public class TelebotApplication {

    public static final List<Product> products = new CopyOnWriteArrayList<>();
    public static final Map<User, Person> persons = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(TelebotApplication.class, args);
    }

    @PostConstruct
    protected void init() {
        Random generator = new Random(new Date().getTime());
        for (int i = 0; i < 10; i++) {
            products.add(new Product("Продукт" + i, generator.nextDouble() * 100));
        }
    }

}
