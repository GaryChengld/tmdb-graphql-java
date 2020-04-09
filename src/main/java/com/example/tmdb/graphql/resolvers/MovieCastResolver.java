package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.types.Genre;
import com.example.tmdb.graphql.types.MovieCast;
import com.example.tmdb.graphql.types.Video;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Gary Cheng
 */
@Component
public class MovieCastResolver extends AbstractMovieResolver<MovieCast> implements GraphQLResolver<MovieCast> {

    public List<Genre> getGenres(MovieCast movie) {
        return this.getGenres(movie.getGenreIds());
    }

    public Video getTrailer(MovieCast movie) {
        return this.getTrailer(movie.getId());
    }
}
