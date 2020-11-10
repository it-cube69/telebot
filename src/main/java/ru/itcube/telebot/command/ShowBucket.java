package ru.itcube.telebot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.itcube.telebot.TelebotApplication;
import ru.itcube.telebot.domain.Person;
import ru.itcube.telebot.domain.Product;

public class ShowBucket extends Command {

    Logger LOG = LoggerFactory.getLogger(ShowBucket.class);

    private final AbsSender telegramBot;
    private final Update request;


    public ShowBucket(AbsSender telegramBot, Update request) {
        this.telegramBot = telegramBot;
        this.request = request;
    }

    @Override
    public void execute() {
        final User user = request.getMessage().getFrom();
        final Person person = TelebotApplication.persons.getOrDefault(user, null);
        if (person != null) {
            String response = "";
            for (Product product : person.getBucket().getProducts()) {
                response = response + product.toString() + "\n";
            }
            if (response == "") {
                response = "Продуктов в корзине нет";
            }

            sentTextMessage(request.getMessage().getChatId().toString(), response);
        }
    }

    private void sentTextMessage(String chatId, String text) {
        try {
            telegramBot.execute(new SendMessage(chatId, text));
        } catch (TelegramApiException e) {
            LOG.error("ERROR: {}", e.getMessage());
        }
    }
}
