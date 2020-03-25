package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.tmdb.graphql.services.MovieService;
import com.example.tmdb.graphql.types.Credits;
import com.example.tmdb.graphql.types.Images;
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
        if (selectionSet.contains("casts") || selectionSet.contains("crews")) {
            appendToResponse = this.addToAppended(appendToResponse, "credits");
        }
        if (selectionSet.contains("images")) {
            appendToResponse = this.addToAppended(appendToResponse, "images");
        }
        if (selectionSet.contains("videos")) {
            appendToResponse = this.addToAppended(appendToResponse, "videos");
        }
        if (selectionSet.contains("recommendations")) {
            appendToResponse = this.addToAppended(appendToResponse, "recommendations");
        }
        
        log.debug("appendToResponse:{}", appendToResponse);
        if (StringUtils.isEmpty(appendToResponse)) {
            return movieService.getMovieDetail(id);
        } else {
            return movieService.getMovieDetail(id, appendToResponse);
        }
    }

    public Credits movieCredits(long id) {
    	log.debug("Received movieCredits request, id={}", id);
    	return movieService.getMovieCredits(id);
    }
    
    public Images movieImages(long id) {
    	log.debug("Received movieImages request, id={}", id);
    	return movieService.getMovieImages(id);
    }
    public MoviePageResults nowPlayingMovies(Integer page, String region) {
        log.debug("Received nowPlayingMovies request, page={}, region={}", page, region);
        return movieService.nowPlaying(page, region);
    }
    
    public MoviePageResults popularMovies(Integer page, String region) {
        log.debug("Received popularMovies request, page={}, region={}", page, region);
        return movieService.popularMovies(page, region);
    }
    
    public MoviePageResults topRatedMovies(Integer page, String region) {
        log.debug("Received topRatedMovies request, page={}, region={}", page, region);
        return movieService.topRatedrMovies(page, region);
    }
    
    public MoviePageResults upcomingMovies(Integer page, String region) {
        log.debug("Received upcomingMovies request, page={}, region={}", page, region);
        return movieService.upcomingMovies(page, region);
    }

    public MoviePageResults movieRecommendations(long id, Integer page) {
        log.debug("Received movieRecommendations request, id={}, page={}", id, page);
        return movieService.getMovieRecommendations(id, page);
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
