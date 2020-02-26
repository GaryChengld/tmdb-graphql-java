package com.example.tmdb.graphql.resolvers;

import com.example.tmdb.graphql.services.CommonCodeService;
import com.example.tmdb.graphql.services.TmdbHelper;
import com.example.tmdb.graphql.types.BaseMovie;
import com.example.tmdb.graphql.types.ImageSize;
import com.example.tmdb.graphql.types.Language;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gary Cheng
 */
public abstract class AbstractMovieResolver<T extends BaseMovie> {
    @Autowired
    private TmdbHelper tmdbHelper;
    @Autowired
    private CommonCodeService commonCodeService;

    public Language getOriginalLanguage(T movie) {
        return null == movie.getOriginalLanguage() ? null : commonCodeService.getLanguageByCode(movie.getOriginalLanguage());
    }

    public String getBackdropPath(T movie, ImageSize size) {
        return this.tmdbHelper.getBackdropPath(size, movie.getBackdropPath());
    }

    public String getPosterPath(T movie, ImageSize size) {
        return this.tmdbHelper.getPosterPath(size, movie.getPosterPath());
    }
}
