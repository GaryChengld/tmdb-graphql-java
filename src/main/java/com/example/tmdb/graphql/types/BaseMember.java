package com.example.tmdb.graphql.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @author Gary Cheng
 */
@Data
public class BaseMember {
    private Integer id;
    @JsonAlias("credit_id")
    private String creditId;
    private String name;
    @JsonAlias("profile_path")
    private String profilePath;
}
