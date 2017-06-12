package com.spotippos.loader;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.spotippos.exception.IllegalLocationException;
import com.spotippos.model.Point;
import com.spotippos.model.Properties;
import com.spotippos.model.Property;
import com.spotippos.service.ProvinceService;

@Configuration
public class PropertiesLoader {

    private static final Logger LOGGER = LogManager.getLogger(PropertiesLoader.class);

    private ProvinceService provinceService;
    private String fileName;

    @Autowired
    public PropertiesLoader(ProvinceService provinceService, @Value("${files.properties}") String fileName) {
        this.provinceService = provinceService;
        this.fileName = fileName;
    }

    @Bean
    public Map<Integer, Property> properties() {
        Properties properties = JsonLoader.loadFile(fileName, Properties.class);

        return properties.getProperties().stream().map(property -> {

            Point point = new Point(property.getX(), property.getY());

            try {
                List<String> names = provinceService.findNamesBy(point);
                property.setProvinces(names);
                return property;
            } catch (IllegalLocationException e) {
                LOGGER.error(String.format("Excluindo propriedade id %d devido localização inválida!", property.getId()), e);
                return null;
            }

        }).filter(property -> Objects.nonNull(property)).collect(Collectors.toMap(Property::getId, Function.identity()));
    }

}
