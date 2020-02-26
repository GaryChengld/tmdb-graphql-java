package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.services.CommonCodeService;
import com.example.tmdb.graphql.services.MovieService;
import com.example.tmdb.graphql.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gary Cheng
 */
@Component
public class MovieDetailResolver extends AbstractMovieResolver<MovieDetail> implements GraphQLResolver<MovieDetail> {
    @Autowired
    private CommonCodeService commonCodeService;
    @Autowired
    private MovieService movieService;

    public List<Language> getSpokenLanguages(MovieDetail movie) {
        return movie.getSpokenLanguages().stream().map(l -> commonCodeService.getLanguageByCode(l.getCode())).collect(Collectors.toList());
    }

    public List<CastMember> getCasts(MovieDetail movie, Integer top) {
        if (null == movie.getCredits()) {
            movie.setCredits(movieService.getMovieCredits(movie.getId()));
        }
        if (null != top && top > 0 && top < movie.getCredits().getCasts().size()) {
            return movie.getCredits().getCasts().subList(0, top);
        } else {
            return movie.getCredits().getCasts();
        }
    }

    public List<CrewMember> getCrews(MovieDetail movie, String job) {
        if (null == movie.getCredits()) {
            movie.setCredits(movieService.getMovieCredits(movie.getId()));
        }
        return movie.getCredits().getCrews();
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
