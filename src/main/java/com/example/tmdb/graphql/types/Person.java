package com.example.tmdb.graphql.types;

import com.example.tmdb.graphql.mappers.TmdbDateDeserializer;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Gary Cheng
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    private long id;
    private String name;
    @JsonAlias("known_for_department")
    private String knownForDepartment;
    @JsonDeserialize(using = TmdbDateDeserializer.class)
    private Date birthday;
    @JsonDeserialize(using = TmdbDateDeserializer.class)
    private Date deathday;
    @JsonAlias("also_known_as")
    private List<String> alsoKnownAs;
    private int gender;
    private String biography;
    private Double popularity;
    @JsonAlias("place_of_birth")
    private String placeOfBirth;
    @JsonAlias("profile_path")
    private String profilePath;
    @JsonAlias("imdb_id")
    private String imdbId;
    private String homepage;
}
