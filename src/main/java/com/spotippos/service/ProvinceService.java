package com.spotippos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotippos.exception.IllegalLocationException;
import com.spotippos.model.Boundaries;
import com.spotippos.model.Point;
import com.spotippos.model.Province;

@Service
public class ProvinceService {

    private List<Province> provinces;

    @Autowired
    public ProvinceService(List<Province> provinces) {
        this.provinces = provinces;
    }

    public List<String> findNamesBy(Point point) throws IllegalLocationException {

        List<String> provincesName = provinces.stream().filter(province -> {
            Boundaries boundaries = province.getBoundaries();
            Point upperLeft = boundaries.getUpperLeft();
            Point bottomRight = boundaries.getBottomRight();

            return (point.getX() >= upperLeft.getX() && point.getX() <= bottomRight.getX()) 
                    && (point.getY() >= bottomRight.getY() && point.getY() <= upperLeft.getY());
        }).map(Province::getName).collect(Collectors.toList());

        if (provincesName.isEmpty()) {
            throw new IllegalLocationException("A localização informada não existe");
        }

        return provincesName;
    }
}
