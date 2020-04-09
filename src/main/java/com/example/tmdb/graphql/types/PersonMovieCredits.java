package com.example.tmdb.graphql.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Gary Cheng
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonMovieCredits {
    private long id;
    @JsonAlias("cast")
    private List<MovieCast> casts;
    @JsonAlias("crew")
    private List<MovieCrew> crews;

    public PersonMovieCredits sort() {
        Collections.sort(this.casts, (c1, c2) -> this.compareDate(c1.getReleaseDate(), c2.getReleaseDate()));
        Collections.sort(this.crews, (c1, c2) -> this.compareDate(c1.getReleaseDate(), c2.getReleaseDate()));
        return this;
    }

    private int compareDate(Date date1, Date date2) {
        if (null != date1 && null != date2) {
            return -1 * date1.compareTo(date2);
        } else if (null != date1 && null == date2) {
            return -1;
        } else if (null == date1 && null != date2) {
            return 1;
        } else {
            return 0;
        }
    }
}
