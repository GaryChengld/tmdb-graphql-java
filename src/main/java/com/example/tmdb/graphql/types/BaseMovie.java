package com.example.tmdb.graphql.types;

import java.util.Date;
import java.util.List;

import com.example.tmdb.graphql.mappers.TmdbDateDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Data;

/**
 * @author Gary Cheng
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseMovie {
    private long id;
    private boolean adult;
    private String title;
    private String overview;
    private Double popularity;
    @JsonProperty("original_title")
    private String originalTitle;
    @JsonProperty("original_language")
    private String originalLanguage;
    @JsonProperty("backdrop_path")
    private String backdropPath;
    @JsonProperty("poster_path")
    public String posterPath;
    private List<Genre> genres;
    @JsonProperty("genre_ids")
    private List<Integer> genre_ids;
    @JsonProperty("vote_average")
    private Double voteAverage;
    @JsonProperty("vote_count")
    private Integer voteCount;
    @JsonProperty("release_date")
    @JsonDeserialize(using = TmdbDateDeserializer.class)
    private Date releaseDate;
}
