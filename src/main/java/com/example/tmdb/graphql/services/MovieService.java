package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.FeignConfig;
import com.example.tmdb.graphql.types.Credits;
import com.example.tmdb.graphql.types.MovieDetail;
import com.example.tmdb.graphql.types.MoviePageResults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
     * Get movie primary information with appended data
     *
     * @param id               movie id
     * @param appendToResponse the appended data to response
     * @return the movie detail
     */
    @RequestMapping(path = "/{id}", method = GET)
    MovieDetail getMovieDetail(@PathVariable("id") Long id, @RequestParam(name = "append_to_response") String appendToResponse);

    /**
     * Get the cast and crew for a movie
     *
     * @param id the id of the movie
     * @return the cast and crew for movie
     */
    @RequestMapping(path = "/{id}/credits", method = GET)
    Credits getMovieCredits(@PathVariable("id") Long id);

    /**
     * Get a list of movies in theatres.
     *
     * @param page   the page to query
     * @param region the region to filter
     * @return the list of movies.s
     */
    @RequestMapping(path = "/now_playing", method = GET)
    MoviePageResults nowPlaying(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "region") String region);
}
