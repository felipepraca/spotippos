package com.spotippos.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spotippos.exception.IllegalLocationException;
import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.model.Point;
import com.spotippos.model.Property;
import com.spotippos.repository.PropertyRepository;
import com.spotippos.validator.PropertyValidator;

/**
 * Classe que prove os serviços de propriedades.
 * 
 * @author Felipe
 *
 */
@Component
public class PropertyService {

    private List<PropertyValidator> validators;
    private ProvinceService provinceService;
    private PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(List<PropertyValidator> validators, ProvinceService provinceService, PropertyRepository propertyRepository) {
        this.validators = validators;
        this.provinceService = provinceService;
        this.propertyRepository = propertyRepository;
    }

    /**
     * Cria uma nova propriedade.
     * 
     * @param property
     *            (propriedade a ser salva)
     * @return
     * @throws InvalidPropertyException
     */
    public int create(Property property) throws InvalidPropertyException {
        validate(property);

        Point point = new Point(property.getX(), property.getY());

        try {
            List<String> names = provinceService.findNamesBy(point);
            property.setProvinces(names);
        } catch (IllegalLocationException e) {
            throw new InvalidPropertyException(e.getMessage());
        }

        Property propertyInPoint = propertyRepository.findBy(point);

        if (Objects.nonNull(propertyInPoint)) {
            throw new InvalidPropertyException(String.format("Existe uma outra propriedade neste local. X: %d, Y: %d", point.getX(), point.getY()));
        }

        return propertyRepository.save(property);
    }

    /**
     * Valida propriedade com todas as regras de negocios de limites.
     * 
     * @param property
     *            (propriedade a ser validada)
     * @throws InvalidPropertyException
     */
    private void validate(Property property) throws InvalidPropertyException {
        for (PropertyValidator validator : validators) {
            validator.validate(property);
        }
    }

}
