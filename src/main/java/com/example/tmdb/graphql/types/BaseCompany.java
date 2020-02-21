package com.example.tmdb.graphql.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseCompany {
	private Integer id;
	private String name;
	@JsonProperty("logo_path")
	private String logoPath;
	@JsonProperty("origin_country")
	private String originCountry;
}
