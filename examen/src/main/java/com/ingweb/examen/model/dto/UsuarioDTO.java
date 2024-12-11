package com.ingweb.examen.model.dto;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

//TODO
@Data
public class UsuarioDTO {
    @Id
    private ObjectId _id;
    private String email;
    private LocalDate timestamp;
    private LocalDate caducidad;
}
