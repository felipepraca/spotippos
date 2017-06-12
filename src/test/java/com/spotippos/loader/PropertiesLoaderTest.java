package com.spotippos.loader;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.spotippos.exception.IllegalLocationException;
import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.model.Point;
import com.spotippos.model.Property;
import com.spotippos.service.PropertyService;
import com.spotippos.service.ProvinceService;

@RunWith(MockitoJUnitRunner.class)
public class PropertiesLoaderTest {

    @Mock
    private ProvinceService provinceService;

    @Mock
    private PropertyService propertyService;

    private String fileName = "propertiesTest.json";

    @Test
    public void carregaJsonDePropriedades() throws IllegalLocationException, InvalidPropertyException {
        // GIVEN
        when(provinceService.findNamesBy(any(Point.class))).thenReturn(Arrays.asList("Provincia A"));

        // Exclui a propriedade de id 8.
        doThrow(IllegalLocationException.class).when(provinceService).findNamesBy(eq(new Point(10, 1401)));

        // WHEN
        new PropertiesLoader(provinceService, propertyService, fileName);

        // THEN
        verify(provinceService, times(8)).findNamesBy(any(Point.class));
        verify(propertyService, times(7)).create(any(Property.class));
    }

    @Test
    public void erroAoCriarPropriedadesMasNaoQuebraOFluxo() throws IllegalLocationException, InvalidPropertyException {
        // GIVEN
        when(provinceService.findNamesBy(any(Point.class))).thenReturn(Arrays.asList("Provincia A"));

        // Exclui a propriedade de id 8.
        doThrow(IllegalLocationException.class).when(provinceService).findNamesBy(eq(new Point(10, 1401)));

        // Erro para criar qualquer coisa.
        doThrow(InvalidPropertyException.class).when(propertyService).create(any(Property.class));

        // WHEN
        new PropertiesLoader(provinceService, propertyService, fileName);

        // THEN
        verify(provinceService, times(8)).findNamesBy(any(Point.class));
        verify(propertyService, times(7)).create(any(Property.class));
    }
}
