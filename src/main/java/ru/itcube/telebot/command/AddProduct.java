package ru.itcube.telebot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.itcube.telebot.TelebotApplication;
import ru.itcube.telebot.domain.Person;
import ru.itcube.telebot.domain.Product;

public class AddProduct extends Command {

    private final Update request;

    public AddProduct(Update request) {
        this.request = request;
    }

    @Override
    public void execute() {
        final String text = request.getMessage().getText();
        final String[] payload = text.split(" ");
        final Long productId = Long.valueOf(payload[1]);
        final Product product = TelebotApplication.products.remove(productId);
        if (product != null) {
            final User user = request.getMessage().getFrom();
            final Person person = TelebotApplication.persons.get(user);
            person.getBucket().addProduct(product);
        }
    }
}
