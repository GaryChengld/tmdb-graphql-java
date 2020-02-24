package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.types.ImageSize;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Gary Cheng
 */
@Component
public class TmdbHelper {
    @Value("${tmdb.image.baseURL}")
    private String imageBaseURL;
    @Value("${tmdb.image.size.backdrop}")
    private String[] backdropSizes;
    @Value("${tmdb.image.size.poster}")
    private String[] posterSizes;
    @Value("${tmdb.image.size.logo}")
    private String[] logoSizes;
    @Value("${tmdb.image.size.profile}")
    private String[] profileSizes;

    public String getBackdropPath(ImageSize size, String fileName) {
        return this.getImagePath(backdropSizes, size, fileName);
    }

    public String getPosterPath(ImageSize size, String fileName) {
        return this.getImagePath(posterSizes, size, fileName);
    }

    public String getLogoPath(ImageSize size, String fileName) {
        return this.getImagePath(logoSizes, size, fileName);
    }

    public String getProfilePath(ImageSize size, String fileName) {
        return this.getImagePath(profileSizes, size, fileName);
    }

    private String getImagePath(String[] sizes, ImageSize size, String fileName) {
        return null == fileName ? null : imageBaseURL + sizes[size.ordinal()] + fileName;
    }
}
