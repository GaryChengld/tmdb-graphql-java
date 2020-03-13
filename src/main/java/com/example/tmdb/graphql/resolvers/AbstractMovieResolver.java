package com.example.tmdb.graphql.resolvers;

import com.example.tmdb.graphql.services.CommonCodeService;
import com.example.tmdb.graphql.services.MovieService;
import com.example.tmdb.graphql.services.TmdbHelper;
import com.example.tmdb.graphql.types.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Gary Cheng
 */
public abstract class AbstractMovieResolver<T extends BaseMovie> {
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

    public Images getImages(T movie) {
        if (null == movie.getImages()) {
            movie.setImages(movieService.getMovieImages(movie.getId()));
        }
        return movie.getImages();
    }

    public List<Video> getVideos(T movie) {
        if (null == movie.getVideos()) {
            movie.setVideos(movieService.getMovieVideos(movie.getId()));
        }
        return movie.getVideos().getResults();
    }

}
