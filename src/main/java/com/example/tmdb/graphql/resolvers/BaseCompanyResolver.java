package com.example.tmdb.graphql.resolvers;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.services.TmdbHelper;
import com.example.tmdb.graphql.types.BaseCompany;
import com.example.tmdb.graphql.types.ImageSize;

/**
 * @author Gary Cheng
 */
@Component
public class BaseCompanyResolver implements GraphQLResolver<BaseCompany> {
    private final TmdbHelper tmdbHelper;

    public BaseCompanyResolver(TmdbHelper tmdbHelper) {
        this.tmdbHelper = tmdbHelper;
    }

    public String getLogoPath(BaseCompany company, ImageSize size) {
        return this.tmdbHelper.getLogoPath(size, company.getLogoPath());
    }
}
