package ru.turnikman.jokes.service;

import org.springframework.stereotype.Service;
import ru.turnikman.jokes.model.Joke;

import java.util.List;
import java.util.Optional;

@Service
public interface JokeService {
    boolean addJoke(Joke joke);
    List<Joke> getAllJokes();
    Optional<Joke> getJokeById(Long id);
    boolean deleteJokeById(Long id);
    boolean changeJokeById(Long id, Joke joke);
}
