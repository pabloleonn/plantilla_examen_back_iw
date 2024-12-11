package com.ingweb.examen.model.dto;

import lombok.Data;
import org.bson.types.ObjectId;

//TODO
@Data
public class ObjetoDTO {
    private ObjectId _id;
    private double lat;
    private double lon;
    private String url;
    private String calle;
}
