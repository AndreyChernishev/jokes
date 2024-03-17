package ru.turnikman.jokes.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.turnikman.jokes.model.Joke;
import ru.turnikman.jokes.repository.JokesRepository;

import java.util.Optional;

@Service
public class TelegramBotService  {

    private final TelegramBot telegramBot; //Забираем наш бин с ботом из Spring-а

    public TelegramBotService(@Autowired TelegramBot telegramBot) { //Конструктор для вставки бина
        this.telegramBot = telegramBot;
        this.telegramBot.setUpdatesListener(updates -> { //Лямбда - регистрируем слушателя обновлений
            updates.forEach(this::buttonClickReact); //В лямбде забираем все обновления - и вызываем обработку их
            return UpdatesListener.CONFIRMED_UPDATES_ALL; //Подтверждаем, что все забрали
        }, Throwable::printStackTrace); //Если поймали ошибку - выводим трейс, чтобы понять, в чем дело
    }

    private void buttonClickReact(Update update) { //Реагируем на событие
        //Optional<Joke> joke = jokesRepository.findById(1);
        String joke = "Joke 1";
        //Подготавливаем сообщение на ответ
        SendMessage request = new SendMessage(update.message().chat().id(), joke) //update.message().chat().id() - Id, в какой чат отправлять сообщение, в данном случае - тому, кто написал
                .parseMode(ParseMode.HTML) //Без понятия, что такое, но было в документации
                .disableWebPagePreview(true) //Без понятия, что такое, но было в документации
                .disableNotification(true) //Без понятия, что такое, но было в документации
                .replyToMessageId(update.message().messageId()); //Делаем наш ответ как ответ на отправленное ранее сообщение
        this.telegramBot.execute(request); //Отправляем подготовленное сообщение
    }

}
