package com.example.tmdb.graphql.mappers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.tmdb.graphql.TmdbConstants.TMDB_DATE_PATTERN;

/**
 * @author Gary Cheng
 */
@Slf4j
public class TmdbDateDeserializer extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        SimpleDateFormat format = new SimpleDateFormat(TMDB_DATE_PATTERN);
        String date = parser.getText();
        if (StringUtils.isEmpty(date)) {
            return null;
        }
        try {
            return format.parse(date);
        } catch (ParseException e) {
            log.info(e.getMessage());
            return null;
        }
    }

}
