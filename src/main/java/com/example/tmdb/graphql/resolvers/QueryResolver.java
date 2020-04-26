package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.tmdb.graphql.services.DiscoverService;
import com.example.tmdb.graphql.services.MovieService;
import com.example.tmdb.graphql.services.PersonService;
import com.example.tmdb.graphql.services.SearchService;
import com.example.tmdb.graphql.types.*;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gary Cheng
 */
@Component
@Slf4j
public class QueryResolver implements GraphQLQueryResolver {
    private final MovieService movieService;
    private final PersonService personService;
    private final SearchService searchService;
    private final DiscoverService discoverService;

    public QueryResolver(MovieService movieService, PersonService personService, SearchService searchService, DiscoverService discoverService) {
        this.movieService = movieService;
        this.personService = personService;
        this.searchService = searchService;
        this.discoverService = discoverService;
    }

    public MovieDetail movieDetail(long id, DataFetchingEnvironment env) {
        log.debug("getMovieDetail id={}", id);
        String appendToResponse = "";
        DataFetchingFieldSelectionSet selectionSet = env.getSelectionSet();
        if (selectionSet.contains("casts") || selectionSet.contains("crews") || selectionSet.contains("director")) {
            appendToResponse = this.addToAppended(appendToResponse, "credits");
        }
        if (selectionSet.contains("images")) {
            appendToResponse = this.addToAppended(appendToResponse, "images");
        }
        if (selectionSet.contains("videos") || selectionSet.contains("trailer")) {
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

    public MoviePageResults searchMovie(String query, Integer page, String region) {
        log.debug("Search movie request, query={}, page={}, region={}", query, page, region);
        return searchService.searchMovie(query, page, region);
    }

    public MoviePageResults moviesByGenre(String genre, Integer page, String region) {
        log.debug("Search movie by genre, genre={}, page={}, region={}", genre, page, region);
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(DiscoverService.PARAM_WITH_GENRES, genre);
        queryMap.put(DiscoverService.PARAM_REGION, region);
        queryMap.put(DiscoverService.PARAM_PAGE, null == page ? "1" : page.toString());
        queryMap.put(DiscoverService.PARAM_SORT_BY, DiscoverService.SORT_BY_POPULARITY_DESC);
        return discoverService.discoverMovie(queryMap);
    }

    public MoviePageResults movieRecommendations(long id, Integer page) {
        log.debug("Received movieRecommendations request, id={}, page={}", id, page);
        return movieService.getMovieRecommendations(id, page);
    }

    public Person person(long id, DataFetchingEnvironment env) {
        log.debug("Received person request, id={}", id);
        String appendToResponse = "";
        DataFetchingFieldSelectionSet selectionSet = env.getSelectionSet();
        if (selectionSet.contains("movieCredits") || selectionSet.equals("knownFor")) {
            appendToResponse = this.addToAppended(appendToResponse, "movie_credits");
        }
        if (selectionSet.contains("images")) {
            appendToResponse = this.addToAppended(appendToResponse, "images");
        }
        if (StringUtils.isEmpty(appendToResponse)) {
            return personService.getPerson(id);
        } else {
            return personService.getPerson(id, appendToResponse);
        }
    }

    public PersonMovieCredits personMovieCredits(long id) {
        log.debug("Received personMovieCredits request, id={}", id);
        return personService.getPersonMovieCredits(id);
    }

    public PersonImages personImages(long id) {
        log.debug("Received personImages request, id={}", id);
        return personService.getPersonImages(id);
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
