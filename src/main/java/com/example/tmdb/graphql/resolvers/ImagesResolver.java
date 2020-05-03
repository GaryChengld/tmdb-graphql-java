package com.example.tmdb.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.tmdb.graphql.services.TmdbHelper;
import com.example.tmdb.graphql.types.ImageSize;
import com.example.tmdb.graphql.types.Images;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Gary Cheng
 */
@Component
public class ImagesResolver implements GraphQLResolver<Images> {
    private final TmdbHelper tmdbHelper;

    public ImagesResolver(TmdbHelper tmdbHelper) {
        this.tmdbHelper = tmdbHelper;
    }

    public List<String> getBackdrops(Images images, ImageSize size) {
        return images.getBackdrops().stream()
                .map(path -> this.tmdbHelper.getBackdropPath(size, path.getFilePath()))
                .collect(Collectors.toList());
    }

    public List<String> getPosters(Images images, ImageSize size) {
        return images.getPosters().stream()
                .map(path -> this.tmdbHelper.getPosterPath(size, path.getFilePath()))
                .collect(Collectors.toList());
    }
}
