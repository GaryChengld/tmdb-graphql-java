package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.types.BaseMovie;
import com.example.tmdb.graphql.types.MovieDetail;

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
        MovieDetail movie = movieService.getMovieDetail(apiKey, 181812L);
        log.debug("Movie:{}", movie);
    }
}
