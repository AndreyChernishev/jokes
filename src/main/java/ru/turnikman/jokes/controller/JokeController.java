package ru.turnikman.jokes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.turnikman.jokes.model.Joke;
import ru.turnikman.jokes.service.JokeService;

import java.util.List;

@RestController
@RequestMapping("/jokes")
@RequiredArgsConstructor
public class JokeController {
    private final JokeService jokeService;

    @PostMapping
    ResponseEntity<Void> addJoke(@RequestBody Joke joke) {
        System.out.println(joke);
        jokeService.addJoke(joke);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<List<Joke>> getJokes() {
        return ResponseEntity.ok(jokeService.getAllJokes());
    }

    @GetMapping("/{id}")
    ResponseEntity<Joke> getJokeById(@PathVariable Long id) {
        return jokeService.getJokeById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
