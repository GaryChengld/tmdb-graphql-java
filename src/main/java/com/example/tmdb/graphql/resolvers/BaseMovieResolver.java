package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.types.BaseMovie;
import org.springframework.stereotype.Component;

/**
 * @author Gary Cheng
 */
@Component
public class BaseMovieResolver extends AbstractMovieResolver<BaseMovie> implements GraphQLResolver<BaseMovie> {
}
