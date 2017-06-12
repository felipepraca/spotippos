package com.spotippos.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.spotippos.model.Point;
import com.spotippos.model.Property;

/**
 * Repositorio que controla "a base de dados" das propriedades.
 * 
 * @author Felipe
 *
 */
@Component
public class PropertyRepository {

    private Map<Integer, Property> properties = new HashMap<>();
    private Map<Point, Property> propertiesInPoint = new HashMap<>();

    /**
     * Salva (adiciona no mapa) uma nova propriedade.
     * 
     * @param property
     *            (novo item)
     * @return
     */
    public int save(Property property) {

        // se não tiver id cria um (propriedades do json já tem id).
        int id = Objects.nonNull(property.getId()) ? property.getId() : propertiesInPoint.size() + 1;
        property.setId(id);

        // adiciona no mapa por id.
        properties.put(id, property);

        // adiciona no mapa por localização.
        propertiesInPoint.put(new Point(property.getX(), property.getY()), property);

        return id;
    }

    /**
     * Procura propriedade por point exato de localização.
     * 
     * @param point
     *            (ponto de localização x e y)
     * @return
     */
    public Property findBy(Point point) {
        return propertiesInPoint.get(point);
    }

    /**
     * Busca propriedade por id.
     * 
     * @param id
     *            (Id da propriedade a ser buscada)
     * @return
     */
    public Property findBy(int id) {
        return properties.get(id);
    }

}
