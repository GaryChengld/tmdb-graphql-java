package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.FeignConfig;
import com.example.tmdb.graphql.types.GenreResults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Gary Cheng
 */
@FeignClient(name = "genreClient", url = "${tmdb.apiURL}", path = "/genre", configuration = FeignConfig.class)
public interface GenreService {

    /**
     * Get the list of official genres for movies.
     *
     * @return the list of genres.
     */
    @RequestMapping(path = "/movie/list", method = GET)
    GenreResults movieGenreList();

    /**
     * Get the list of official genres for tvs.
     *
     * @return the list of genres.
     */
    @RequestMapping(path = "/tv/list", method = GET)
    GenreResults tvGenreList();
}
