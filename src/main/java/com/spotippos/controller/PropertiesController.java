package com.spotippos.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.exception.PropertyNotFound;
import com.spotippos.model.Boundaries;
import com.spotippos.model.Point;
import com.spotippos.model.Properties;
import com.spotippos.model.Property;
import com.spotippos.service.PropertyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Controller de propriedades
 * 
 * @author Felipe
 *
 */
@Api(value = "Properties")
@RestController
@RequestMapping(path = "/properties", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PropertiesController {

    @Autowired
    private PropertyService service;

    /**
     * Cria novas propriedades.
     * 
     * @param property
     *            (corpo da requisição)
     * @param bindingResult
     *            (resultado das validações)
     * @param response
     *            (reposta da requisição)
     * @throws InvalidPropertyException
     */
    @ApiOperation(value = "Adiciona uma nova propriedade", notes = "Adiciona uma nova propriedade")
    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody @Valid Property property, BindingResult bindingResult, HttpServletResponse response) throws InvalidPropertyException {

        // caso exista algum erro na validação dos campos do corpo da requisição lança exceção.
        if (bindingResult.hasErrors()) {
            throw new InvalidPropertyException(bindingResult);
        }

        int id = service.create(property);

        response.addHeader("Location", "/properties/" + id);
        response.setStatus(HttpStatus.CREATED.value());
    }

    /**
     * Consulta propriedade por id,
     * 
     * @param id
     *            (Id da propriedade)
     * @return
     * @throws PropertyNotFound
     */
    @ApiOperation(value = "Busca propriedade por id", notes = "Busca propriedade pelo seu id")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Property get(@ApiParam(value = "ID da propriedade") @PathVariable("id") int id, HttpServletResponse response) throws PropertyNotFound {
        return service.findBy(id);
    }

    /**
     * Consulta propriedades por area.
     * 
     * @param ax
     *            (posição x do ponto superior esquerdo)
     * @param ay
     *            (posição y do ponto superior esquerdo)
     * @param bx
     *            (posição x do ponto inferior esquerdo)
     * @param by
     *            (posição y do ponto inferior esquerdo)
     * @return
     * @throws PropertyNotFound
     */
    @ApiOperation(value = "Busca propriedades por area", notes = "Busca propriedades dentro de uma determinada area")
    @RequestMapping(method = RequestMethod.GET)
    public Properties get(@ApiParam(required = true, value = "Posição X do ponto superior esquerdo") @RequestParam(required = true, value = "ax") int ax,
                        @ApiParam(required = true, value = "Posição Y do ponto superior esquerdo") @RequestParam(required = true, value = "ay") int ay,
                        @ApiParam(required = true, value = "Posição X do ponto inferior direito") @RequestParam(required = true, value = "bx") int bx,
                        @ApiParam(required = true, value = "Posição Y do ponto inferior direito") @RequestParam(required = true, value = "by") int by) 
                      throws PropertyNotFound {

        Point a = new Point(ax, ay);
        Point b = new Point(bx, by);

        // cria area para realizar a busca.
        Boundaries boundaries = new Boundaries(a, b);

        return service.findBy(boundaries);
    }

}
