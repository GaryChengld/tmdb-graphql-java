package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.types.Language;
import com.example.tmdb.graphql.types.MovieDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gary Cheng
 */
@Component
public class MovieDetailResolver extends AbstractMovieResolver<MovieDetail> implements GraphQLResolver<MovieDetail> {

    public List<String> getSpokenLanguages(MovieDetail movie) {
        return movie.getSpokenLanguages().stream().map(Language::getName).collect(Collectors.toList());
    }
}
