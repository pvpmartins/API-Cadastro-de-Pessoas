package com.example.Attornatus.teste.ErrorHandles;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NaoEncontradoException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NaoEncontradoException() {
        super("Não encontrado");
    }

    public NaoEncontradoException(String message, Throwable cause, boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NaoEncontradoException(String message) {
        super(message);
    }

    public NaoEncontradoException(Throwable cause) {
        super(cause);
    }

}
