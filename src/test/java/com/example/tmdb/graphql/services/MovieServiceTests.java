package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.types.MovieDetail;
import com.example.tmdb.graphql.types.MoviePageResults;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Gary Cheng
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class MovieServiceTests {
    @Autowired
    private MovieService movieService;

    @Test
    public void findMovieById() {
        MovieDetail movie = movieService.getMovieDetail(181812L);
        log.debug("Movie:{}", movie);
        assertNotNull(movie);
    }

    @Test
    public void nowPlaying() {
        MoviePageResults results = movieService.nowPlaying(1, null);
        log.debug("Movie now playing:{}", results);
        assertNotNull(results);
    }
}
