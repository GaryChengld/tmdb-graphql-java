package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.FeignConfig;
import com.example.tmdb.graphql.types.MoviePageResults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Gary Cheng
 */
@FeignClient(name = "searchClient", url = "${tmdb.apiURL}", path = "/search", configuration = FeignConfig.class)
public interface SearchService {
    /**
     * Search movie
     *
     * @param query a text query to search
     * @param page which page to search
     * @param region the region to filter
     * @return the list of movies
     */
    @RequestMapping(path = "/movie", method = GET)
    MoviePageResults searchMovie(@RequestParam(name = "query") String query, @RequestParam(name = "page", defaultValue = "1") Integer page,
                                 @RequestParam(name = "region") String region);
}
