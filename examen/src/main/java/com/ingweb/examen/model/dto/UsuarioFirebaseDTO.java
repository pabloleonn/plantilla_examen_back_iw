package com.ingweb.examen.model.dto;

import lombok.Data;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class UsuarioFirebaseDTO implements Serializable {

    private String accessToken;
    private String email;
    private LocalDate caducidad;
}
