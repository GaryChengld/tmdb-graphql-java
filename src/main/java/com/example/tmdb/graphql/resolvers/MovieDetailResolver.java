package com.example.tmdb.graphql.resolvers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.services.MovieService;
import com.example.tmdb.graphql.types.Credits;
import com.example.tmdb.graphql.types.Images;
import com.example.tmdb.graphql.types.Language;
import com.example.tmdb.graphql.types.MovieDetail;
import com.example.tmdb.graphql.types.Video;

/**
 * @author Gary Cheng
 */
@Component
public class MovieDetailResolver extends AbstractMovieResolver<MovieDetail> implements GraphQLResolver<MovieDetail> {
    @Autowired
    private MovieService movieService;

    public List<String> getSpokenLanguages(MovieDetail movie) {
        return movie.getSpokenLanguages().stream().map(Language::getName).collect(Collectors.toList());
    }

    public Credits getCredits(MovieDetail movie) {
        if (null == movie.getCredits()) {
            movie.setCredits(movieService.getMovieCredits(movie.getId()));
        }
        return movie.getCredits();
    }
    
    public Images getImages(MovieDetail movie) {
        if (null == movie.getImages()) {
            movie.setImages(movieService.getMovieImages(movie.getId()));
        }
        return movie.getImages();
    }
    
    public List<Video> getVideos(MovieDetail movie) {
        if (null == movie.getVideos()) {
            movie.setVideos(movieService.getMovieVideos(movie.getId()));
        }
        return movie.getVideos().getResults();
    }
}
