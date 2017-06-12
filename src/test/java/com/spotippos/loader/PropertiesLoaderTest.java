package com.spotippos.loader;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spotippos.exception.IllegalLocationException;
import com.spotippos.model.Point;
import com.spotippos.model.Property;
import com.spotippos.service.ProvinceService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PropertiesLoaderTest {

    @Mock
    private ProvinceService provinceService;

    private String fileName = "propertiesTest.json";

    @InjectMocks
    private PropertiesLoader loader = new PropertiesLoader(provinceService, fileName);

    @Test
    public void carregaJsonDePropriedades() throws IllegalLocationException {
        // GIVEN
        when(provinceService.findNamesBy(any(Point.class))).thenReturn(Arrays.asList("Provincia A"));

        // Exclui a propriedade de id 8
        doThrow(IllegalLocationException.class).when(provinceService).findNamesBy(eq(new Point(10, 1401)));

        // WHEN
        Map<Integer, Property> properties = loader.properties();

        // THEN
        assertEquals(7, properties.size());

        Property property = properties.get(1);

        assertEquals(643000, property.getPrice(), 0.0001);
        assertEquals("Imóvel em Scavy", property.getDescription());
        assertEquals(Arrays.asList("Provincia A"), property.getProvinces());
    }
}
