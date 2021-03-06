package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.types.BaseMovie;
import com.example.tmdb.graphql.types.Genre;
import com.example.tmdb.graphql.types.Video;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Gary Cheng
 */
@Component
public class BaseMovieResolver extends AbstractMovieResolver<BaseMovie> implements GraphQLResolver<BaseMovie> {

    public List<Genre> getGenres(BaseMovie movie) {
        return this.getGenres(movie.getGenreIds());
    }

    public Video getTrailer(BaseMovie movie) {
        return this.getTrailer(movie.getId());
    }
}
