package com.unisporti.api_unisporti.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.util.List;
import java.util.Map;

@Getter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MalformedRequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    private final List<Map<String, String>> errors;

    public MalformedRequestException(String message) {
        super(message);
        this.errors = List.of(Map.of("error", message));
    }

    public MalformedRequestException(List<Map<String, String>> errors) {
        super("Requisição malformada");
        this.errors = errors;
    }
}