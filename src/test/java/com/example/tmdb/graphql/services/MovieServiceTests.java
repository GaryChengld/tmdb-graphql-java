package com.example.tmdb.graphql.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tmdb.graphql.types.MovieDetail;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Gary Cheng
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class MovieServiceTests {
    @Autowired
    private MovieService movieService;
    private String apiKey = "2456b3bf911743418b857ac8bc9f24e3";

    @Test
    public void findMovieById() {
        MovieDetail movie = movieService.getMovieDetail(apiKey, 181812L);
        log.debug("Movie:{}", movie);
        assertNotNull(movie);
    }
}
