package com.spotippos.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.spotippos.model.Point;
import com.spotippos.model.Property;

public class PropertyRepositoryTest {

    private PropertyRepository repository = new PropertyRepository();

    @Test
    public void salvaPropriedadeComId() {
        // GIVEN
        Property property = new Property();
        property.setId(10);
        property.setX(1000);
        property.setY(1000);

        // WHEN
        int id = repository.save(property);

        // THEN
        assertEquals(10, id);
    }

    @Test
    public void salvaPropriedadeSemId() {
        // GIVEN
        Property property = new Property();
        property.setX(1000);
        property.setY(1000);

        // WHEN
        int id = repository.save(property);

        // THEN
        assertEquals(1, id);
    }

    @Test
    public void procuraPropriedadePorPonto() {
        // GIVEN
        salvaPropriedadeSemId();

        Point point = new Point(1000, 1000);

        // WHEN
        Property property = repository.findBy(point);

        // THEN
        assertEquals(1, property.getId().intValue());
        assertEquals(1000, property.getX().intValue());
        assertEquals(1000, property.getY().intValue());
    }

}
