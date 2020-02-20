package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.services.TmdbHelper;
import com.example.tmdb.graphql.types.ImageSize;
import com.example.tmdb.graphql.types.MovieDetail;
import org.springframework.stereotype.Component;

/**
 * @author Gary Cheng
 */
@Component
public class MovieDetailResolver implements GraphQLResolver<MovieDetail> {
    private final TmdbHelper tmdbHelper;

    public MovieDetailResolver(TmdbHelper tmdbHelper) {
        this.tmdbHelper = tmdbHelper;
    }

    public String getBackdropPath(MovieDetail movie, ImageSize size) {
        return this.tmdbHelper.getBackdropPath(size, movie.getBackdropPath());
    }

    public String getPosterPath(MovieDetail movie, ImageSize size) {
        return this.tmdbHelper.getPosterPath(size, movie.getPosterPath());
    }
}
