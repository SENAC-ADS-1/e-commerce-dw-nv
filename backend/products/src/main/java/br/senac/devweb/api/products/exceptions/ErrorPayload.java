package br.senac.devweb.api.products.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorPayload {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

}