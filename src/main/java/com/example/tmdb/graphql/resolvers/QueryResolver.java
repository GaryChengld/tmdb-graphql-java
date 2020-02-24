package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.tmdb.graphql.services.MovieService;
import com.example.tmdb.graphql.types.MovieDetail;
import com.example.tmdb.graphql.types.MoviePageResults;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author Gary Cheng
 */
@Component
@Slf4j
public class QueryResolver implements GraphQLQueryResolver {
    private final MovieService movieService;

    public QueryResolver(MovieService movieService) {
        this.movieService = movieService;
    }

    public MovieDetail movieDetail(long id, DataFetchingEnvironment env) {
        log.debug("getMovieDetail id={}", id);
        String appendToResponse = "";
        DataFetchingFieldSelectionSet selectionSet = env.getSelectionSet();
        if (selectionSet.contains("Credits")) {
            appendToResponse = this.addToAppended(appendToResponse, "credits");
        }
        log.debug("appendToResponse:{}", appendToResponse);
        if (StringUtils.isEmpty(appendToResponse)) {
            return movieService.getMovieDetail(id);
        } else {
            return movieService.getMovieDetail(id, appendToResponse);
        }
    }

    public MoviePageResults movieNowPlaying(Integer page, String region) {
        log.debug("Received movieNowPlaying request, page={}, region={}", page, region);
        return movieService.nowPlaying(page, region);
    }

    private String addToAppended(String appendToResponse, String value) {
        if (StringUtils.isEmpty(appendToResponse)) {
            return value;
        } else {
            return appendToResponse + "," + value;
        }
    }

    private boolean containsField(DataFetchingEnvironment env, String fieldName) {
        return env.getSelectionSet().getFields().stream().anyMatch(f -> f.getName().equals(fieldName));
    }
}
