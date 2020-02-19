package com.example.tmdb.graphql.resolvers;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.tmdb.graphql.services.MovieService;
import com.example.tmdb.graphql.types.MovieDetail;

import lombok.extern.slf4j.Slf4j;

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

	public MovieDetail movieDetail(String apiKey, long id) {
		log.debug("getMovieDetail apiKey={}, id={}", apiKey, id);
		return movieService.getMovieDetail(apiKey, id);
	}
}
