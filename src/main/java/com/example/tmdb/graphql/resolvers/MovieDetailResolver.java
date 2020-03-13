package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.types.CastMember;
import com.example.tmdb.graphql.types.CrewMember;
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
}
