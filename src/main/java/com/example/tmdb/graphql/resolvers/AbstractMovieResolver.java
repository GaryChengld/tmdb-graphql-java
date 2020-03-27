package com.example.tmdb.graphql.resolvers;

import com.example.tmdb.graphql.services.CommonCodeService;
import com.example.tmdb.graphql.services.MovieService;
import com.example.tmdb.graphql.services.TmdbHelper;
import com.example.tmdb.graphql.types.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gary Cheng
 */
public abstract class AbstractMovieResolver<T extends BaseMovie> {
    private static final String VIDEO_TYPE_TRAILER = "Trailer";

    @Autowired
    protected TmdbHelper tmdbHelper;
    @Autowired
    protected CommonCodeService commonCodeService;
    @Autowired
    protected MovieService movieService;

    public Language getOriginalLanguage(T movie) {
        return null == movie.getOriginalLanguage() ? null : commonCodeService.getLanguageByCode(movie.getOriginalLanguage());
    }

    public String getBackdropPath(T movie, ImageSize size) {
        return this.tmdbHelper.getBackdropPath(size, movie.getBackdropPath());
    }

    public String getPosterPath(T movie, ImageSize size) {
        return this.tmdbHelper.getPosterPath(size, movie.getPosterPath());
    }

    public Integer getReleaseYear(T movie) {
        if (null == movie.getReleaseDate()) {
            return null;
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(movie.getReleaseDate());
            return calendar.get(Calendar.YEAR);
        }
    }
}
