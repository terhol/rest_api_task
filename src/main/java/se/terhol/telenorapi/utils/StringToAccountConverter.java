package se.terhol.telenorapi.utils;

import org.springframework.core.convert.converter.Converter;
import se.terhol.telenorapi.model.Account;

public class StringToAccountConverter implements Converter<String, Account> {

    @Override
    public Account convert(String source) {
        try {
            return Account.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}


