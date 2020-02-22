package com.example.tmdb.graphql.services;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.example.tmdb.graphql.types.MoviePageResults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.tmdb.graphql.FeignConfig;
import com.example.tmdb.graphql.types.MovieDetail;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Gary Cheng
 */
@FeignClient(name = "movieClient", url = "${tmdb.apiURL}", path = "/movie", configuration = FeignConfig.class)
public interface MovieService {
    /**
     * Get the primary information about a movie.
     *
     * @param id the id of movie
     * @return the movie details
     */
    @RequestMapping(path = "/{id}", method = GET)
    MovieDetail getMovieDetail(@PathVariable("id") Long id);

    /**
     * Get a list of movies in theatres.
     *
     * @param page the page to query
     * @param region the region to filter
     * @return the list of movies.s
     */
    @RequestMapping(path = "/now_playing", method = GET)
    MoviePageResults nowPlaying(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "region") String region);
}
