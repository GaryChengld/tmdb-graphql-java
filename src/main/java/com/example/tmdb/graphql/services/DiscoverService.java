package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.FeignConfig;
import com.example.tmdb.graphql.types.MoviePageResults;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Gary Cheng
 */
@FeignClient(name = "discoverClient", url = "${tmdb.apiURL}", path = "/discover", configuration = FeignConfig.class)
public interface DiscoverService {
    public static final String PARAM_REGION = "region";
    public static final String PARAM_SORT_BY = "sort_by";
    public static final String PARAM_WITH_GENRES = "with_genres";
    public static final String PARAM_PAGE = "page";
    public static final String PARAM_WITH_YEAR = "primary_release_year";
    public static final String PARAM_WITH_VOTE_AVERAGE_GTE = "vote_average.gte";

    public static final String SORT_BY_POPULARITY_ASC = "popularity.asc";
    public static final String SORT_BY_POPULARITY_DESC = "popularity.desc";

    @RequestMapping(path = "/movie", method = GET)
    MoviePageResults discoverMovie(@SpringQueryMap Map<String, String> queryMap);
}
