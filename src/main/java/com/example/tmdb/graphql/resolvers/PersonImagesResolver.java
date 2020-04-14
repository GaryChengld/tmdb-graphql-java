package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.services.TmdbHelper;
import com.example.tmdb.graphql.types.ImageSize;
import com.example.tmdb.graphql.types.PersonImages;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gary Cheng
 */
@Component
public class PersonImagesResolver implements GraphQLResolver<PersonImages> {
    private final TmdbHelper tmdbHelper;

    public PersonImagesResolver(TmdbHelper tmdbHelper) {
        this.tmdbHelper = tmdbHelper;
    }

    public List<String> getProfiles(PersonImages images, ImageSize size) {
        return images.getProfiles().stream()
                .map(path -> this.tmdbHelper.getProfilePath(size, path.getFilePath()))
                .collect(Collectors.toList());
    }
}
