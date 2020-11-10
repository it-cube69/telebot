package ru.itcube.telebot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.itcube.telebot.TelebotApplication;
import ru.itcube.telebot.domain.Product;

public class ListProducts extends Command {

    Logger LOG = LoggerFactory.getLogger(ListProducts.class);

    private final AbsSender telegramBot;
    private final Update request;

    public ListProducts(AbsSender telegramBot, Update request) {
        this.telegramBot = telegramBot;
        this.request = request;
    }

    @Override
    public void execute() {
        final String chatId = request.getMessage().getChatId().toString();
        String response = "";
        for (Product product : TelebotApplication.products.values()) {
            response = response + product.toString() + "\n";
        }
        sentTextMessage(chatId, response);
    }

    private void sentTextMessage(String chatId, String text) {
        try {
            telegramBot.execute(new SendMessage(chatId, text));
        } catch (TelegramApiException e) {
            LOG.error("ERROR: {}", e.getMessage());
        }
    }
}
