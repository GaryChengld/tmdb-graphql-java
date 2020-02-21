package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.types.Language;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Gary Cheng
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ConfigurationServiceTests {
    @Autowired
    private ConfigurationService configurationService;

    @Test
    public void languages() {
        List<Language> languages = configurationService.languages();
        log.debug("languages:{}", languages);
        assertTrue(languages.size() > 0);
    }
}
