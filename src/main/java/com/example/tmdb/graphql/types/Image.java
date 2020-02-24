package com.example.tmdb.graphql.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author Gary Cheng
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
    @JsonAlias("file_path")
    private String filePath;
    private Integer width;
    private Integer height;
    @JsonAlias("aspect_ratio")
    private Double aspectRatio;
    @JsonAlias("vote_average")
    private Double voteAverage;
    @JsonAlias("vote_count")
    private Integer voteCount;
}
