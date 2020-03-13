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
public class BaseMovie {
    private long id;
    private boolean adult;
    private String title;
    private String overview;
    private Double popularity;
    @JsonAlias("original_title")
    private String originalTitle;
    @JsonAlias("original_language")
    private String originalLanguage;
    @JsonAlias("backdrop_path")
    private String backdropPath;
    @JsonAlias("poster_path")
    public String posterPath;
    @JsonAlias("genre_ids")
    private List<Integer> genreIds;
    @JsonAlias("vote_average")
    private Double voteAverage;
    @JsonAlias("vote_count")
    private Integer voteCount;
    @JsonAlias("release_date")
    @JsonDeserialize(using = TmdbDateDeserializer.class)
    private Date releaseDate;
    private Images images;
    private Videos videos;
}
