package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.types.Movie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Gary Cheng
 */
@SpringBootTest
@Slf4j
public class MovieServiceTests {
    @Autowired
    private MovieService movieService;
    private String apiKey = "2456b3bf911743418b857ac8bc9f24e3";

    @Test
    public void findMovieById() {
        Movie movie = movieService.findMovieById(apiKey, 181812);
        log.debug("Movie:{}", movie);
    }
}
