package com.spotippos.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.model.Property;

/**
 * Validador dos limites de banheiros de uma propriedade.
 * 
 * @author Felipe
 *
 */
@Component
public class BathsValidator implements PropertyValidator {

    private int max;
    private int min;

    public BathsValidator(@Value("${validation.baths.max}") int max, @Value("${validation.baths.min}") int min) {
        this.max = max;
        this.min = min;
    }

    @Override
    public void validate(Property property) throws InvalidPropertyException {

        if (property.getBaths() > max || property.getBaths() < min) {
            throw new InvalidPropertyException(String.format("Propriedade inválida por número de banheiros. Max: %d, Min: %d, Propriedade: %d", max, min, property.getBaths()));
        }

    }

}
