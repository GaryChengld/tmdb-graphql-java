package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.FeignClientConfiguration;
import com.example.tmdb.graphql.types.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Gary Cheng
 */
@FeignClient(value = "movieClient", url = "${tmdb.apiURL}/movie", configuration = FeignClientConfiguration.class)
public interface MovieService {
    @RequestMapping(value = "/{id}", method = GET)
    Movie findMovieById(@RequestParam(name = "api_key") String apiKey, @PathVariable("id") Integer id);
}
