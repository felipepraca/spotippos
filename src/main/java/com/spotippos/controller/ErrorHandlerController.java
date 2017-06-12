package com.spotippos.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spotippos.exception.InvalidPropertyException;

/**
 * Interceptador para transformar exceções em erros mais amigáveis para o usuário. 
 * 
 * @author Felipe
 *
 */
@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(InvalidPropertyException.class)
    public void handleBadRequests(HttpServletResponse response, InvalidPropertyException exception) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

}
