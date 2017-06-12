package com.spotippos.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spotippos.exception.InvalidPropertyException;
import com.spotippos.model.Property;
import com.spotippos.service.PropertyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

}
