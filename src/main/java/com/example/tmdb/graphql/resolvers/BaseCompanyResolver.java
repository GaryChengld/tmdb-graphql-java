package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.services.TmdbHelper;
import com.example.tmdb.graphql.types.BaseCompany;
import com.example.tmdb.graphql.types.ImageSize;
import org.springframework.stereotype.Component;

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
