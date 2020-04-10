package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.services.PersonService;
import com.example.tmdb.graphql.services.TmdbHelper;
import com.example.tmdb.graphql.types.BaseMovie;
import com.example.tmdb.graphql.types.ImageSize;
import com.example.tmdb.graphql.types.Person;
import com.example.tmdb.graphql.types.PersonMovieCredits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gary Cheng
 */
@Component
public class PersonResolver implements GraphQLResolver<Person> {
    private static final String ACTING_DEPARTMENT = "Acting";

    @Autowired
    private TmdbHelper tmdbHelper;
    @Autowired
    private PersonService personService;

    public String getProfilePath(Person person, ImageSize size) {
        return this.tmdbHelper.getProfilePath(size, person.getProfilePath());
    }

    public PersonMovieCredits getMovieCredits(Person person) {
        if (null == person.getMovieCredits()) {
            person.setMovieCredits(this.personService.getPersonMovieCredits(person.getId()).sort());
        }
        return person.getMovieCredits();
    }

    public List<BaseMovie> getKnownFor(Person person) {
        PersonMovieCredits movieCredits = this.getMovieCredits(person);
        List<BaseMovie> movies = new ArrayList<>();
        if (ACTING_DEPARTMENT.equalsIgnoreCase(person.getKnownForDepartment())) {
            movies.addAll(movieCredits.getCasts());
        } else {
            movies.addAll(movieCredits.getCrews().stream()
                    .filter(crew -> person.getKnownForDepartment().equalsIgnoreCase(crew.getDepartment()))
                    .collect(Collectors.toList()));
        }
        return movies.stream()
                .filter(m -> null != m.getPopularity() && m.getPopularity() > 0)
                .sorted((m1, m2) -> m2.getPopularity().compareTo(m1.getPopularity()))
                .limit(20)
                .collect(Collectors.toList());
    }
}
