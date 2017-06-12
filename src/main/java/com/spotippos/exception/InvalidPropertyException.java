package com.spotippos.exception;

import org.springframework.validation.BindingResult;

public class InvalidPropertyException extends Exception {

    private static final long serialVersionUID = -4139598879719627471L;

    public InvalidPropertyException(String massege) {
        super(massege);
    }

    public InvalidPropertyException(BindingResult bindingResult) {
        super(bindingResult.getAllErrors().get(0).getDefaultMessage());
    }

}
