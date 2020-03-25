package com.example.tmdb.graphql.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author Gary Cheng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetail extends BaseMovie {
    @JsonAlias("imdb_id")
    private String imdbId;
    private List<Genre> genres;
    private Integer runtime;
    private Long budget;
    private Long revenue;
    private String status;
    private String tagLine;
    @JsonAlias("production_countries")
    private List<Country> productionCountries;
    @JsonAlias("production_companies")
    private List<BaseCompany> productionCompanies;
    @JsonAlias("spoken_languages")
    private List<Language> spokenLanguages;
    private Credits credits;
    private MoviePageResults recommendations;
}
