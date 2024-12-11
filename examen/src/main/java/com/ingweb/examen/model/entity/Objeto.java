package com.ingweb.examen.model.entity;

import com.ingweb.examen.model.dto.DTO;
import com.ingweb.examen.model.dto.ObjetoDTO;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//TODO
@Data
@Document(collection="Objeto")
public class Objeto implements DTO<ObjetoDTO> {

    @Id
    private ObjectId _id;
    private double lat;
    private double lon;
    private String url;
    private String calle;
    //TODO
    @Override
    public ObjetoDTO toDTO() {
        ObjetoDTO objetoDTO = new ObjetoDTO();
        objetoDTO.set_id(_id);
        objetoDTO.setUrl(url);
        objetoDTO.setCalle(calle);
        objetoDTO.setLat(lat);
        objetoDTO.setLon(lon);
        return objetoDTO;
    }
}
