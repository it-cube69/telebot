package ru.itcube.telebot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.itcube.telebot.TelebotApplication;
import ru.itcube.telebot.domain.Bucket;
import ru.itcube.telebot.domain.Person;

public class CreatePerson extends Command {
    private final Update request;

    public CreatePerson(Update request) {
        this.request = request;
    }

    @Override
    public void execute() {
        final User user = request.getMessage().getFrom();
        final Person person = new Person(user.getId(), user.getFirstName(), user.getLastName());
        final Bucket bucket = new Bucket(person);
        person.setBucket(bucket);
        TelebotApplication.persons.putIfAbsent(user, person);
    }
}
