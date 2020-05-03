package com.example.tmdb.graphql.scalars;

import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.tmdb.graphql.TmdbConstants.TMDB_DATE_PATTERN;

@Component
public class DateScalarType extends GraphQLScalarType {

    private final static DateFormat DATE_FORMAT = new SimpleDateFormat(TMDB_DATE_PATTERN);

    public DateScalarType() {
        super("Date", "Date Scalar", new Coercing<Date, String>() {
            @Override
            public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
                Date date = (Date) dataFetcherResult;
                return null == date ? null : DATE_FORMAT.format(date);
            }

            @Override
            public Date parseValue(Object input) throws CoercingParseValueException {
                return parseLiteral(input);
            }

            @Override
            public Date parseLiteral(Object input) throws CoercingParseLiteralException {
                if (input instanceof StringValue) {
                    try {
                        return DATE_FORMAT.parse(((StringValue) input).getValue());
                    } catch (ParseException e) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        });
    }
}
