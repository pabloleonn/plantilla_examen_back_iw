package com.ingweb.examen.model.dto;

import java.util.List;

public class UbicacionDTO {
    private int place_id;
    private String license;
    private String osm_type;
    private int osm_id;
    private String lat;
    private String lon;
    private String type;
    private int place_rank;
    private double importance;
    private String addresstype;
    private String name;
    private String display_name;
    private List<String> boundingbox;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
