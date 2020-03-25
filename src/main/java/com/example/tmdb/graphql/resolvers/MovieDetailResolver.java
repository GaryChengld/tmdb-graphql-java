package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
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

    public List<CrewMember> getDirector(MovieDetail movie) {
        return this.getCrews(movie, JOB_DIRECTOR);
    }

    public MoviePageResults getRecommendations(MovieDetail movie) {
        if (null == movie.getRecommendations()) {
            movie.setRecommendations(movieService.getMovieRecommendations(movie.getId(), 1));
        }
        return movie.getRecommendations();
    }
}
