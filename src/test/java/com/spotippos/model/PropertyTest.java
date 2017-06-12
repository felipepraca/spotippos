package com.spotippos.model;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Field;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.junit.Test;

public class PropertyTest {

    @Test
    public void validaAnotacoesDeValidacao() throws NoSuchFieldException, SecurityException {

        Field x = Property.class.getDeclaredField("x");
        Field y = Property.class.getDeclaredField("y");
        Field title = Property.class.getDeclaredField("title");
        Field price = Property.class.getDeclaredField("price");
        Field description = Property.class.getDeclaredField("description");
        Field beds = Property.class.getDeclaredField("beds");
        Field baths = Property.class.getDeclaredField("baths");
        Field squareMeters = Property.class.getDeclaredField("squareMeters");

        validaNaoNulo(x);
        validaNaoNulo(y);
        validaNaoNulo(title);
        validaNaoVazio(title);
        validaNaoNulo(price);
        validaNaoNulo(description);
        validaNaoVazio(description);
        validaNaoNulo(beds);
        validaNaoNulo(baths);
        validaNaoNulo(squareMeters);
    }

    private void validaNaoNulo(Field field) {
        NotNull annotation = field.getAnnotation(NotNull.class);
        assertNotNull(annotation);
    }

    private void validaNaoVazio(Field field) {
        NotBlank annotation = field.getAnnotation(NotBlank.class);
        assertNotNull(annotation);
    }
}
