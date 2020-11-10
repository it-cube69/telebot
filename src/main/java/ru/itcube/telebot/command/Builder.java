package ru.itcube.telebot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class Builder {

    private final Update request;
    private final AbsSender telegramBot;

    public Builder(Update request, AbsSender telegramBot) {
        this.request = request;
        this.telegramBot = telegramBot;
    }

    public Command build() {
        final String text = request.getMessage().getText();
        if ("list".equals(text)) {
            return new ListProducts(telegramBot, request);
        } else if ("show bucket".equals(text)) {
            return new ShowBucket(telegramBot, request);
        } else if (text.matches("add \\d")) {
            return new AddProduct(request);
        } else {
            return new NoAction();
        }
    }

}
