package com.example.tmdb.graphql.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseCompany {
    private Integer id;
    private String name;
    @JsonAlias("logo_path")
    private String logoPath;
    @JsonAlias("origin_country")
    private String originCountry;
}
