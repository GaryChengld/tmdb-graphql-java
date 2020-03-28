package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.TmdbConstants;
import com.example.tmdb.graphql.types.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gary Cheng
 */
@Component
public class MovieDetailResolver extends AbstractMovieResolver<MovieDetail> implements GraphQLResolver<MovieDetail> {
    private static final String JOB_DIRECTOR = "Director";
    private static final String DEPARTMENT_WRITING = "Writing";

    public Images getImages(MovieDetail movie) {
        if (null == movie.getImages()) {
            movie.setImages(movieService.getMovieImages(movie.getId()));
        }
        return movie.getImages();
    }

    public List<Video> getVideos(MovieDetail movie, String type) {
        if (null == movie.getVideos()) {
            movie.setVideos(movieService.getMovieVideos(movie.getId()));
        }
        if (null == type) {
            return movie.getVideos().getResults();
        } else {
            return movie.getVideos().getResults().stream().filter(v -> v.getType().equalsIgnoreCase(type)).collect(Collectors.toList());
        }
    }

    public Video getTrailer(MovieDetail movie) {
        if (null == movie.getVideos()) {
            movie.setVideos(movieService.getMovieVideos(movie.getId()));
        }
        return movie.getVideos().getResults().stream()
                .filter(v -> v.getType().equalsIgnoreCase(TmdbConstants.VIDEO_TYPE_TRAILER))
                .findFirst()
                .orElse(null);
    }

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
        if (StringUtils.isEmpty(job)) {
            return movie.getCredits().getCrews();
        } else {
            return movie.getCredits().getCrews().stream().filter(c -> job.equalsIgnoreCase(c.getJob())).collect(Collectors.toList());
        }
    }

    public List<CrewMember> getDirectors(MovieDetail movie) {
        return this.getCrews(movie, JOB_DIRECTOR);
    }

    public List<CrewMember> getWriters(MovieDetail movie) {
        if (null == movie.getCredits()) {
            movie.setCredits(movieService.getMovieCredits(movie.getId()));
        }
        return movie.getCredits().getCrews().stream()
                .filter(c -> DEPARTMENT_WRITING.equalsIgnoreCase(c.getDepartment()))
                .collect(Collectors.toList());
    }

    public MoviePageResults getRecommendations(MovieDetail movie) {
        if (null == movie.getRecommendations()) {
            movie.setRecommendations(movieService.getMovieRecommendations(movie.getId(), 1));
        }
        return movie.getRecommendations();
    }
}
