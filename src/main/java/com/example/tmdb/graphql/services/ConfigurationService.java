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
@FeignClient(value = "configurationClient", url = "${tmdb.apiURL}/configuration", configuration = FeignConfig.class)
public interface ConfigurationService {
    @RequestMapping(value = "/languages", method = GET)
    List<Language> languages();
}
