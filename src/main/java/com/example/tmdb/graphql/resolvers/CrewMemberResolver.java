package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.services.TmdbHelper;
import com.example.tmdb.graphql.types.CrewMember;
import com.example.tmdb.graphql.types.ImageSize;
import org.springframework.stereotype.Component;

/**
 * @author Gary Cheng
 */
@Component
public class CrewMemberResolver implements GraphQLResolver<CrewMember> {
    private final TmdbHelper tmdbHelper;

    public CrewMemberResolver(TmdbHelper tmdbHelper) {
        this.tmdbHelper = tmdbHelper;
    }

    public String getProfilePath(CrewMember crewMember, ImageSize size) {
        return this.tmdbHelper.getProfilePath(size, crewMember.getProfilePath());
    }
}
