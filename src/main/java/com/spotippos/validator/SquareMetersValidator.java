package com.spotippos.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.model.Property;

/**
 * Validador dos limites de metros quadrados de uma propriedade.
 * 
 * @author Felipe
 *
 */
@Component
public class SquareMetersValidator implements PropertyValidator {

    private int max;
    private int min;

    public SquareMetersValidator(@Value("${validation.squareMeters.max}") int max, @Value("${validation.squareMeters.min}") int min) {
        this.max = max;
        this.min = min;
    }

    @Override
    public void validate(Property property) throws InvalidPropertyException {

        if (property.getSquareMeters() > max || property.getSquareMeters() < min) {
            throw new InvalidPropertyException(String.format("Propriedade inválida por quantidade de metros quadrados. Max: %d, Min: %d, Propriedade: %d", max, min, property.getSquareMeters()));
        }

    }

}
