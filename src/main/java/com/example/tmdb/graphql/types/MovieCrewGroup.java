package com.example.tmdb.graphql.types;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Gary Cheng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieCrewGroup {
    private String department;
    private List<MovieCrew> crews;
}
