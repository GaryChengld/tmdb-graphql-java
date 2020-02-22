package com.example.tmdb.graphql.services;

import com.example.tmdb.graphql.FeignConfig;
import com.example.tmdb.graphql.types.Language;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Gary Cheng
 */
@FeignClient(name = "configurationClient", url = "${tmdb.apiURL}", path = "/configuration", configuration = FeignConfig.class)
public interface ConfigurationService {
    /**
     * Get the list of official languages
     *
     * @return the list of languages
     */
    @RequestMapping(path = "/languages", method = GET)
    List<Language> languages();
}
