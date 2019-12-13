package se.terhol.telenorapi.Utils;

import org.springframework.core.convert.converter.Converter;
import se.terhol.telenorapi.model.BusinessType;

public class StringToBusinessTypeConverter implements Converter<String, BusinessType> {
    @Override
    public BusinessType convert(String source) {
        try {
            return BusinessType.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
