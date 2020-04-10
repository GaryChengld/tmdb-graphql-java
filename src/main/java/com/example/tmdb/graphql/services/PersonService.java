package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.FeignConfig;
import com.example.tmdb.graphql.types.MovieDetail;
import com.example.tmdb.graphql.types.Person;
import com.example.tmdb.graphql.types.PersonMovieCredits;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * Get person data with appended data
     *
     * @param id               the id of the person
     * @param appendToResponse the appended data to response
     * @return the person info
     */
    @RequestMapping(path = "/{id}", method = GET)
    Person getPerson(@PathVariable("id") Long id, @RequestParam(name = "append_to_response") String appendToResponse);

    /**
     * Get movie credits of a person
     *
     * @param id the id of person
     * @return the movie credit
     */
    @RequestMapping(path = "/{id}/movie_credits", method = GET)
    PersonMovieCredits getPersonMovieCredits(@PathVariable("id") Long id);
}
