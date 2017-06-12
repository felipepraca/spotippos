package com.spotippos.loader;

import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.spotippos.exception.IllegalLocationException;
import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.model.Point;
import com.spotippos.model.Properties;
import com.spotippos.service.PropertyService;
import com.spotippos.service.ProvinceService;

/**
 * Classe que carrega arquivos json de propriedades (Imóveis).
 * 
 * @author Felipe
 *
 */
@Configuration
public class PropertiesLoader {

    private static final Logger LOGGER = LogManager.getLogger(PropertiesLoader.class);

    private ProvinceService provinceService;
    private PropertyService propertyService;
    private String fileName;

    @Autowired
    public PropertiesLoader(ProvinceService provinceService, PropertyService propertyService, @Value("${files.properties}") String fileName) {
        this.provinceService = provinceService;
        this.propertyService = propertyService;
        this.fileName = fileName;

        properties();
    }

    private void properties() {
        Properties properties = JsonLoader.loadFile(fileName, Properties.class);

        properties.getProperties().stream().map(property -> {

            Point point = new Point(property.getX(), property.getY());

            try {
                List<String> names = provinceService.findNamesBy(point);
                property.setProvinces(names);
                return property;
            } catch (IllegalLocationException e) {
                LOGGER.error(String.format("Excluindo propriedade id %d devido localização inválida!", property.getId()), e);
                return null;
            }

        // filtra nulos que estão fora dos perimetros das provincias;
        }).filter(property -> Objects.nonNull(property))
        .forEach(property -> {
            try {
                propertyService.create(property);
            } catch (InvalidPropertyException e ) {
                LOGGER.error(String.format("Excluindo propriedade id %d devido erro!", property.getId()), e);
            }
        });
    }

}
