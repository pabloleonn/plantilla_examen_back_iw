package com.ingweb.examen.model.entity;

import com.ingweb.examen.model.dto.DTO;
import com.ingweb.examen.model.dto.UsuarioDTO;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

//TODO
@Data
@Document(collection="Usuario")
public class Usuario implements DTO<UsuarioDTO> {
    @Id
    private ObjectId _id;
    private String email;
    private LocalDate timestamp;
    private LocalDate caducidad;

    //TODO
    @Override
    public UsuarioDTO toDTO() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.set_id(_id);
        usuarioDTO.setEmail(email);
        usuarioDTO.setTimestamp(timestamp);
        usuarioDTO.setCaducidad(caducidad);
        return usuarioDTO;
    }
}
