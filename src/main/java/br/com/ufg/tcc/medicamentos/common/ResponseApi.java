package br.com.ufg.tcc.medicamentos.common;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ResponseApi {

    private String message;
    private HttpStatus status;

}
