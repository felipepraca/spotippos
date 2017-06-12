package com.spotippos.validator;

import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.model.Property;

/**
 * Interface para validadores de limites de uma propriedade.
 * 
 * @author Felipe
 *
 */
public interface PropertyValidator {

    void validate(Property property) throws InvalidPropertyException;

}
