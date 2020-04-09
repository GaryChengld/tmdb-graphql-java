package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.types.Genre;
import com.example.tmdb.graphql.types.MovieCrew;
import com.example.tmdb.graphql.types.Video;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Gary Cheng
 */
@Component
public class MovieCrewResolver extends AbstractMovieResolver<MovieCrew> implements GraphQLResolver<MovieCrew> {

    public List<Genre> getGenres(MovieCrew movie) {
        return this.getGenres(movie.getGenreIds());
    }

    public Video getTrailer(MovieCrew movie) {
        return this.getTrailer(movie.getId());
    }
}
