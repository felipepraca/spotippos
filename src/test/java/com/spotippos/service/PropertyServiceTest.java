package com.spotippos.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.spotippos.exception.IllegalLocationException;
import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.exception.PropertyNotFound;
import com.spotippos.model.Boundaries;
import com.spotippos.model.Point;
import com.spotippos.model.Properties;
import com.spotippos.model.Property;
import com.spotippos.repository.PropertyRepository;
import com.spotippos.validator.BedsValidator;
import com.spotippos.validator.PropertyValidator;

@RunWith(MockitoJUnitRunner.class)
public class PropertyServiceTest {

    @Mock
    private BedsValidator bedsValidator;

    @Mock
    private ProvinceService provinceService;

    @Mock
    private PropertyRepository propertyRepository;

    private List<PropertyValidator> validators = new ArrayList<>();

    @InjectMocks
    private PropertyService service = new PropertyService(validators, provinceService, propertyRepository);

    @Before
    public void init() {
        validators.add(bedsValidator);
    }

    @Test
    public void criaPropriedade() throws InvalidPropertyException, IllegalLocationException {
        // GIVEN
        Property property = new Property();
        property.setX(10);
        property.setY(10);

        Point point = new Point(10, 10);

        doNothing().when(bedsValidator).validate(eq(property));
        when(provinceService.findNamesBy(point)).thenReturn(Arrays.asList("A"));
        when(propertyRepository.findBy(point)).thenReturn(null);
        when(propertyRepository.save(eq(property))).thenReturn(1000);

        InOrder inOrder = inOrder(bedsValidator, provinceService, propertyRepository);

        // WHEN
        int id = service.create(property);

        // THEN
        assertEquals(1000, id);

        inOrder.verify(bedsValidator, times(1)).validate(eq(property));
        inOrder.verify(provinceService, times(1)).findNamesBy(eq(point));
        inOrder.verify(propertyRepository, times(1)).findBy(eq(point));
        inOrder.verify(propertyRepository, times(1)).save(eq(property));

        inOrder.verifyNoMoreInteractions();
    }

    @Test(expected = InvalidPropertyException.class)
    public void erroAoValidarQuartos() throws InvalidPropertyException, IllegalLocationException {
        // GIVEN
        Property property = new Property();

        doThrow(InvalidPropertyException.class).when(bedsValidator).validate(property);

        // WHEN
        service.create(property);

        // THEN
        fail();

    }

    @Test(expected = InvalidPropertyException.class)
    public void erroAoProcurarNomeDaProvincia() throws InvalidPropertyException, IllegalLocationException {
        // GIVEN
        Property property = new Property();
        property.setX(10);
        property.setY(10);

        Point point = new Point(10, 10);

        doNothing().when(bedsValidator).validate(eq(property));
        doThrow(IllegalLocationException.class).when(provinceService).findNamesBy(point);

        // WHEN
        service.create(property);

        // THEN
        fail();

    }

    @Test(expected = InvalidPropertyException.class)
    public void encontraPropriedadeNoMesmoLugar() throws InvalidPropertyException, IllegalLocationException {
        // GIVEN
        Property property = new Property();
        property.setX(10);
        property.setY(10);

        Point point = new Point(10, 10);

        doNothing().when(bedsValidator).validate(eq(property));
        when(provinceService.findNamesBy(point)).thenReturn(Arrays.asList("A"));
        when(propertyRepository.findBy(point)).thenReturn(new Property());

        // WHEN
        service.create(property);

        // THEN
        fail();

    }

    @Test
    public void encontraPropriedadePorId() throws PropertyNotFound {
        // GIVEN
        Property property = new Property();

        when(propertyRepository.findBy(100)).thenReturn(property);

        // WHEN
        Property propertyFinded = service.findBy(100);

        // THEN
        assertEquals(property, propertyFinded);
    }

    @Test(expected = PropertyNotFound.class)
    public void naoEncontraPropriedadePorId() throws PropertyNotFound {
        // GIVEN
        when(propertyRepository.findBy(100)).thenReturn(null);

        // WHEN
        service.findBy(100);

        // THEN
        fail();
    }

    @Test
    public void encontraPropriedadesPorArea() throws PropertyNotFound {
        // GIVEN
        Property property1 = new Property();
        Property property2 = new Property();

        when(propertyRepository.findBy(any(Boundaries.class))).thenReturn(Arrays.asList(property1, property2));

        // WHEN
        Properties properties = service.findBy(new Boundaries());

        // THEN
        assertEquals(2, properties.getTotalProperties());
        assertEquals(property1, properties.getProperties().get(0));
        assertEquals(property2, properties.getProperties().get(1));
    }

    @Test(expected = PropertyNotFound.class)
    public void lancaExcecaoSeNaoEncontrarPropriedadeNaArea() throws PropertyNotFound {
        // GIVEN
        when(propertyRepository.findBy(any(Boundaries.class))).thenReturn(new ArrayList<>());

        Point a = new Point(20, 30);
        Point b = new Point(10, 60);

        Boundaries boundaries = new Boundaries(a, b);

        // WHEN
        service.findBy(boundaries);

        // THEN
        fail();
    }

}
