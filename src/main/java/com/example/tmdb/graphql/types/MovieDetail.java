package com.example.tmdb.graphql.types;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Gary Cheng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetail extends BaseMovie {
	private Integer runtime;
	private Long budget;
	private Long revenue;
	private String status;
	private String tagLine;
	@JsonProperty("production_countries")
	private List<Country> productionCountries;
	@JsonProperty("production_companies")
	private List<BaseCompany> productionCompanies;
	@JsonProperty("spoken_languages")
	private List<Language> spokenLanguages;
}
