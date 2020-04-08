package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.services.TmdbHelper;
import com.example.tmdb.graphql.types.ImageSize;
import com.example.tmdb.graphql.types.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Gary Cheng
 */
@Component
public class PersonResolver implements GraphQLResolver<Person> {
    @Autowired
    protected TmdbHelper tmdbHelper;

    public String getProfilePath(Person person, ImageSize size) {
        return this.tmdbHelper.getProfilePath(size, person.getProfilePath());
    }
}
