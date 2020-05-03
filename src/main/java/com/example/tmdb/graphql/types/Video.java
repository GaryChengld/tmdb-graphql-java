package com.example.tmdb.graphql.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Gary Cheng
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {
    private String id;
    @JsonAlias("iso_639_1")
    private String language;
    @JsonAlias("iso_3166_1")
    private String country;
    private String key;
    private String name;
    private String site;
    private Integer size;
    private String type;
}
