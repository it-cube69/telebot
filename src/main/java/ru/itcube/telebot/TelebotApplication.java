package ru.itcube.telebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.itcube.telebot.domain.Person;
import ru.itcube.telebot.domain.Product;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootApplication
public class TelebotApplication {

    public static final Map<Long, Product> products = new ConcurrentHashMap<>();
    public static final Map<User, Person> persons = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(TelebotApplication.class, args);
    }

    @PostConstruct
    protected void init() {
        Random generator = new Random(new Date().getTime());
        for (int i = 0; i < 10; i++) {
            products.put(Long.valueOf(i), new Product(Long.valueOf(i), "Продукт" + i, generator.nextDouble() * 100));
        }
    }

}
