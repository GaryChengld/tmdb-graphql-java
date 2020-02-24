package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.services.TmdbHelper;
import com.example.tmdb.graphql.types.CastMember;
import com.example.tmdb.graphql.types.ImageSize;
import org.springframework.stereotype.Component;

/**
 * @author Gary Cheng
 */
@Component
public class CastMemberResolver implements GraphQLResolver<CastMember> {
    private final TmdbHelper tmdbHelper;

    public CastMemberResolver(TmdbHelper tmdbHelper) {
        this.tmdbHelper = tmdbHelper;
    }

    public String getProfilePath(CastMember castMember, ImageSize size) {
        return this.tmdbHelper.getProfilePath(size, castMember.getProfilePath());
    }
}
