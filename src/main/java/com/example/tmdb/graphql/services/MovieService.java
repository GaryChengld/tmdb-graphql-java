package com.example.tmdb.graphql.services;

import static com.example.tmdb.graphql.TmdbConstants.PARAM_API_KEY;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tmdb.graphql.FeignClientConfiguration;
import com.example.tmdb.graphql.types.MovieDetail;

/**
 * @author Gary Cheng
 */
@FeignClient(value = "movieClient", url = "${tmdb.apiURL}/movie", configuration = FeignClientConfiguration.class)
public interface MovieService {
    @RequestMapping(value = "/{id}", method = GET)
    MovieDetail getMovieDetail(@RequestParam(name = PARAM_API_KEY) String apiKey, @PathVariable("id") Long id);
}
