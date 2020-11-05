package ru.itcube.telebot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.itcube.telebot.TelebotApplication;
import ru.itcube.telebot.domain.Product;

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
        final String chatId = update.getMessage().getChatId().toString();
        final Message message = update.getMessage();
        if (message.getText().equals("list")) {
            String response = "";
            for (Product product: TelebotApplication.products) {
                response = response + product.toString() + "\n";
            }
            sentTextMessage(chatId, response);
        }

    }

    private void sentTextMessage(String chatId, String text) {
        try {
            execute(new SendMessage(chatId, text));
        } catch (TelegramApiException e) {
            LOG.error("ERROR: {}", e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return "";
    }
}
