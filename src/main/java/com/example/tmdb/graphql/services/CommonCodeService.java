package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.types.Language;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Gary Cheng
 */
@Component
public class CommonCodeService {
    private final ConfigurationService configurationService;
    private List<Language> languages;

    public CommonCodeService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public List<Language> getLanguages() {
        return null;
    }
}
