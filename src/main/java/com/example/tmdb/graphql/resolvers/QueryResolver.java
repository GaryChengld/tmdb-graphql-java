package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.tmdb.graphql.services.MovieService;
import com.example.tmdb.graphql.types.MovieDetail;
import com.example.tmdb.graphql.types.MoviePageResults;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.extern.slf4j.Slf4j;
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
        DataFetchingFieldSelectionSet selectionSet = env.getSelectionSet();
        log.debug("selectSet contans backdropPath {}", this.containsField(env, "backdropPath"));
        return movieService.getMovieDetail(id);
    }

    public MoviePageResults movieNowPlaying(Integer page, String region) {
        log.debug("Received movieNowPlaying request, page={}, region={}", page, region);
        return movieService.nowPlaying(page, region);
    }

    private boolean containsField(DataFetchingEnvironment env, String fieldName) {
        return env.getSelectionSet().getFields().stream().anyMatch(f -> f.getName().equals(fieldName));
    }
}
