package ru.itcube.telebot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.itcube.telebot.command.Builder;
import ru.itcube.telebot.command.CreatePerson;

@Component
public class SimpleBot extends TelegramLongPollingBot {

    Logger LOG = LoggerFactory.getLogger(SimpleBot.class);

    @Override
    public String getBotToken() {
        return "";
    }

    @Override
    public void onUpdateReceived(Update update) {
        LOG.info("Update: {}", update);
        new CreatePerson(update).execute();
        new Builder(update, this).build().execute();
    }

    @Override
    public String getBotUsername() {
        return "";
    }
}
