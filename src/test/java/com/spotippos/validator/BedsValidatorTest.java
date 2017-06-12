package com.spotippos.validator;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.model.Property;

public class BedsValidatorTest {

    private BedsValidator validator = new BedsValidator(5, 2);

    @Test(expected = InvalidPropertyException.class)
    public void maximoInvalido() throws InvalidPropertyException {
        // GIVEN
        Property property = new Property();
        property.setBeds(6);

        // WHEN
        validator.validate(property);

        // THEN
        fail();
    }

    @Test
    public void maximoValido() throws InvalidPropertyException {
        // GIVEN
        Property property = new Property();
        property.setBeds(4);

        // WHEN
        validator.validate(property);

        // THEN
    }

    @Test(expected = InvalidPropertyException.class)
    public void minimoInvalido() throws InvalidPropertyException {
        // GIVEN
        Property property = new Property();
        property.setBeds(1);

        // WHEN
        validator.validate(property);

        // THEN
        fail();
    }

    @Test
    public void minimoValido() throws InvalidPropertyException {
        // GIVEN
        Property property = new Property();
        property.setBeds(2);

        // WHEN
        validator.validate(property);

        // THEN
    }

}
