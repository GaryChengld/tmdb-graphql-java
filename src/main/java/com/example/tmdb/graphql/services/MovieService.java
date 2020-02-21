package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.FeignConfig;
import com.example.tmdb.graphql.types.MovieDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.example.tmdb.graphql.TmdbConstants.PARAM_API_KEY;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Gary Cheng
 */
@FeignClient(value = "movieClient", url = "${tmdb.apiURL}/movie", configuration = FeignConfig.class)
public interface MovieService {
    @RequestMapping(value = "/{id}", method = GET)
    MovieDetail getMovieDetail(@PathVariable("id") Long id);
}
