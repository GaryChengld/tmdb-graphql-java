package com.example.tmdb.graphql.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author Gary Cheng
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Images {
    private List<Image> backdrops;
    private List<Image> posters;
}
