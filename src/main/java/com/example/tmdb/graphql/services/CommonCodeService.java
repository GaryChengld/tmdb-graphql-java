package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.types.Genre;
import com.example.tmdb.graphql.types.Language;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.annotation.PostConstruct;

/**
 * @author Gary Cheng
 */
@Component
public class CommonCodeService {
    private final ConfigurationService configurationService;
    private final GenreService genreService;
    private List<Language> languages;
    private List<Genre> movieGenreList;

    public CommonCodeService(ConfigurationService configurationService, GenreService genreService) {
        super();
        this.configurationService = configurationService;
        this.genreService = genreService;
    }

    public String getLanguageByCode(String code) {
        return this.languages.stream()
                .filter(l -> l.getCode().equalsIgnoreCase(code))
                .findFirst()
                .map(Language::getName)
                .orElse(null);
    }

    public Genre getMovieGenreById(Integer id) {
        return this.movieGenreList.stream()
                .filter(g -> g.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public List<Language> getLanguages() {
        return this.languages;
    }

    @PostConstruct
    private void loadData() {
        this.languages = configurationService.languages();
        this.movieGenreList = genreService.movieGenreList().getGenres();
    }
}
