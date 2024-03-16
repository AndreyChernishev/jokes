package ru.turnikman.jokes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.turnikman.jokes.model.Joke;
import ru.turnikman.jokes.repository.JokesRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JokeServiceImpl implements JokeService {

    private final JokesRepository jokesRepository;

    //    private final TelegramBot telegramBot;
    @Override
    public void addJoke(Joke joke) {
        Optional<Joke> jokeOptional = jokesRepository.findByJoke(joke.getJoke());
        if (jokeOptional.isEmpty()) {
            joke.setId(null);
            jokesRepository.save(joke);
        }
    }
    @Override
    public List<Joke> getAllJokes() {
        return jokesRepository.findAll();
    }

    @Override
    public Optional<Joke> getJokeById(Long id){
        return jokesRepository.findById(id);
    }
}