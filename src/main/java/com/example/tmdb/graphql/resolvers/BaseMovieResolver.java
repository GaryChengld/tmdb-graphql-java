package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.TmdbConstants;
import com.example.tmdb.graphql.types.BaseMovie;
import com.example.tmdb.graphql.types.Genre;
import com.example.tmdb.graphql.types.Video;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gary Cheng
 */
@Component
public class BaseMovieResolver extends AbstractMovieResolver<BaseMovie> implements GraphQLResolver<BaseMovie> {

    public List<Genre> getGenres(BaseMovie movie) {
        return movie.getGenreIds().stream().map(commonCodeService::getMovieGenreById).collect(Collectors.toList());
    }

    public Video getTrailer(BaseMovie movie) {
        return movieService.getMovieVideos(movie.getId()).getResults().stream()
                .filter(v -> v.getType().equalsIgnoreCase(TmdbConstants.VIDEO_TYPE_TRAILER))
                .findFirst()
                .orElse(null);
    }
}
