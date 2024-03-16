package ru.turnikman.jokes.service;

import org.springframework.stereotype.Service;
import ru.turnikman.jokes.model.Joke;

import java.util.List;
import java.util.Optional;

@Service
public interface JokeService {
    void addJoke(Joke joke);
    List<Joke> getAllJokes();
    Optional<Joke> getJokeById(Long id);
}
