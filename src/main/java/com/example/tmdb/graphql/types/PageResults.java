package com.example.tmdb.graphql.types;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

/**
 * @author Gary Cheng
 */
@Data
public abstract class PageResults<T> {
    private Integer page;
    @JsonAlias("total_pages")
    private Integer totalPages;
    @JsonAlias("total_results")
    private Integer totalResults;
    private List<T> results;
}
