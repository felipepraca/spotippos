package com.spotippos.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.model.Property;

/**
 * Validador dos limites de quartos de uma propriedade.
 * 
 * @author Felipe
 *
 */
@Component
public class BedsValidator implements PropertyValidator {

    private int max;
    private int min;

    public BedsValidator(@Value("${validation.beds.max}") int max, @Value("${validation.beds.min}") int min) {
        this.max = max;
        this.min = min;
    }

    @Override
    public void validate(Property property) throws InvalidPropertyException {

        if (property.getBeds() > max || property.getBeds() < min) {
            throw new InvalidPropertyException(String.format("Propriedade inválida por número de quartos. Max: %d, Min: %d, Propriedade: %d", max, min, property.getBeds()));
        }

    }

}
