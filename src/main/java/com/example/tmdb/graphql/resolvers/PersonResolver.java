package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.services.PersonService;
import com.example.tmdb.graphql.services.TmdbHelper;
import com.example.tmdb.graphql.types.ImageSize;
import com.example.tmdb.graphql.types.Person;
import com.example.tmdb.graphql.types.PersonMovieCredits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gary Cheng
 */
@Component
public class PersonResolver implements GraphQLResolver<Person> {
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
}
