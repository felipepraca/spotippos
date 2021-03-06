package com.spotippos.validator;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.model.Property;

public class BathsValidatorTest {

    private BathsValidator validator = new BathsValidator(3, 1);

    @Test(expected = InvalidPropertyException.class)
    public void maximoInvalido() throws InvalidPropertyException {
        // GIVEN
        Property property = new Property();
        property.setBaths(4);

        // WHEN
        validator.validate(property);

        // THEN
        fail();
    }

    @Test
    public void maximoValido() throws InvalidPropertyException {
        // GIVEN
        Property property = new Property();
        property.setBaths(3);

        // WHEN
        validator.validate(property);

        // THEN
    }

    @Test(expected = InvalidPropertyException.class)
    public void minimoInvalido() throws InvalidPropertyException {
        // GIVEN
        Property property = new Property();
        property.setBaths(0);

        // WHEN
        validator.validate(property);

        // THEN
        fail();
    }

    @Test
    public void minimoValido() throws InvalidPropertyException {
        // GIVEN
        Property property = new Property();
        property.setBaths(2);

        // WHEN
        validator.validate(property);

        // THEN
    }

}
