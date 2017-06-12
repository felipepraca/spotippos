package com.spotippos.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.spotippos.exception.IllegalLocationException;
import com.spotippos.model.Boundaries;
import com.spotippos.model.Point;
import com.spotippos.model.Province;

public class ProvinceServiceTest {

    private List<Province> provinces = new ArrayList<>();

    private ProvinceService service = new ProvinceService(provinces);

    @Before
    public void init() {
        Point upperLeft = new Point(0, 20);
        Point bottomRight = new Point(20, 0);

        Boundaries boundaries = new Boundaries();
        boundaries.setUpperLeft(upperLeft);
        boundaries.setBottomRight(bottomRight);

        Province province = new Province();
        province.setName("Province A");
        province.setBoundaries(boundaries);

        provinces.add(province);
    }

    @Test
    public void recuperaNomeDaProvinciaPorPosicao() throws IllegalLocationException {
        // GIVEN
        Point point = new Point(10, 10);

        // WHEN
        List<String> findNamesBy = service.findNamesBy(point);

        // THEN
        assertEquals(Arrays.asList("Province A"), findNamesBy);

    }

    @Test(expected = IllegalLocationException.class)
    public void teste() throws IllegalLocationException {
        // GIVEN
        Point point = new Point(30, 10);

        // WHEN
        service.findNamesBy(point);

        // THEN
        fail();

    }
}
