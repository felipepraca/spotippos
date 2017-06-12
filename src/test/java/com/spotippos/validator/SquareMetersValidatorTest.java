package com.spotippos.validator;

import static org.junit.Assert.fail;

import org.junit.Test;

import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.model.Property;

public class SquareMetersValidatorTest {

    private SquareMetersValidator validator = new SquareMetersValidator(300, 100);

    @Test(expected = InvalidPropertyException.class)
    public void maximoInvalido() throws InvalidPropertyException {
        // GIVEN
        Property property = new Property();
        property.setSquareMeters(400);

        // WHEN
        validator.validate(property);

        // THEN
        fail();
    }

    @Test
    public void maximoValido() throws InvalidPropertyException {
        // GIVEN
        Property property = new Property();
        property.setSquareMeters(200);

        // WHEN
        validator.validate(property);

        // THEN
    }

    @Test(expected = InvalidPropertyException.class)
    public void minimoInvalido() throws InvalidPropertyException {
        // GIVEN
        Property property = new Property();
        property.setSquareMeters(80);

        // WHEN
        validator.validate(property);

        // THEN
        fail();
    }

    @Test
    public void minimoValido() throws InvalidPropertyException {
        // GIVEN
        Property property = new Property();
        property.setSquareMeters(101);

        // WHEN
        validator.validate(property);

        // THEN
    }

}
