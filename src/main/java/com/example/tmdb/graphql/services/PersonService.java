package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.FeignConfig;
import com.example.tmdb.graphql.types.MovieDetail;
import com.example.tmdb.graphql.types.Person;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Gary Cheng
 */
@FeignClient(name = "personClient", url = "${tmdb.apiURL}", path = "/person", configuration = FeignConfig.class)
public interface PersonService {
    /**
     * Get the person data by id
     *
     * @param id the id of person
     * @return the person info
     */
    @RequestMapping(path = "/{id}", method = GET)
    Person getPerson(@PathVariable("id") Long id);
}
