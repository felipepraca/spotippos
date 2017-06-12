package com.spotippos.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.spotippos.model.Boundaries;
import com.spotippos.model.Point;
import com.spotippos.model.Property;

public class PropertyRepositoryTest {

    private PropertyRepository repository = new PropertyRepository();

    @Test
    public void salvaPropriedadeComId() {
        // GIVEN
        Property property = new Property();
        property.setId(10);
        property.setX(900);
        property.setY(900);

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

    @Test
    public void procuraPropriedadePorId() {
        // GIVEN
        salvaPropriedadeSemId();

        // WHEN
        Property property = repository.findBy(1);

        // THEN
        assertEquals(1, property.getId().intValue());
        assertEquals(1000, property.getX().intValue());
        assertEquals(1000, property.getY().intValue());
    }

    @Test
    public void procuraPropriedadePorArea() {
        // GIVEN
        salvaPropriedadeSemId();
        salvaPropriedadeComId();

        Point a = new Point(800, 1200);
        Point b = new Point(1200, 800);

        Boundaries boundaries = new Boundaries(a, b);

        // WHEN
        List<Property> finded = repository.findBy(boundaries);

        // THEN
        assertEquals(2, finded.size());
        assertEquals(1000, finded.get(0).getX().intValue());
        assertEquals(1000, finded.get(0).getY().intValue());
        assertEquals(900, finded.get(1).getX().intValue());
        assertEquals(900, finded.get(1).getY().intValue());
    }

    @Test
    public void procuraPropriedadePorAreaNaoEncontra() {
        // GIVEN
        salvaPropriedadeSemId();
        salvaPropriedadeComId();

        Point a = new Point(200, 900);
        Point b = new Point(400, 400);

        Boundaries boundaries = new Boundaries(a, b);

        // WHEN
        List<Property> finded = repository.findBy(boundaries);

        // THEN
        assertTrue(finded.isEmpty());
    }

}
