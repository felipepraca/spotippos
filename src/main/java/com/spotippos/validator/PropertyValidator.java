package com.spotippos.validator;

import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.model.Property;

public interface PropertyValidator {

    void validate(Property property) throws InvalidPropertyException;

}
