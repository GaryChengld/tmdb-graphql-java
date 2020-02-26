package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.services.CommonCodeService;
import com.example.tmdb.graphql.types.BaseMovie;
import com.example.tmdb.graphql.types.Genre;
import com.example.tmdb.graphql.types.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gary Cheng
 */
@Component
public class BaseMovieResolver extends AbstractMovieResolver<BaseMovie> implements GraphQLResolver<BaseMovie> {
    @Autowired
    private CommonCodeService commonCodeService;

    public List<Genre> getGenres(BaseMovie movie) {
        return movie.getGenreIds().stream().map(commonCodeService::getMovieGenreById).collect(Collectors.toList());
    }
}
