package com.ingweb.examen.service;


import com.ingweb.examen.model.dto.UbicacionDTO;
import com.ingweb.examen.dao.ObjetoRepository;
import com.ingweb.examen.model.dto.ObjetoDTO;
import com.ingweb.examen.model.entity.Objeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class MapaService extends DTOService<ObjetoDTO, Objeto> {

    @Autowired
    private ObjetoRepository objetoRepository;

    public void updateUbicacion(ObjetoDTO objetoDTO, String calle) {
        RestTemplate restTemplate = new RestTemplate();

        String urlAPIMapa = "https://nominatim.openstreetmap.org/search?q=" + calle + "&format=json&limit=1";
        // Cambiar el tipo esperado a una lista de UbicacionDTO
        ResponseEntity<List<UbicacionDTO>> response = restTemplate.exchange(
                urlAPIMapa,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UbicacionDTO>>() {
                }
        );

        // Asegúrate de que la respuesta no esté vacía
        List<UbicacionDTO> ubicaciones = response.getBody();
        if (ubicaciones != null && !ubicaciones.isEmpty()) {
            UbicacionDTO ubicacionDTO = ubicaciones.get(0); // Tomar el primer resultado
            if (!ubicacionDTO.getLat().isEmpty() && !ubicacionDTO.getLon().isEmpty()) {
                Objeto objeto = objetoRepository.findById(objetoDTO.get_id()).get();
                if(objeto != null) {
                    objeto.setLat(Double.parseDouble(ubicacionDTO.getLat()));
                    objeto.setLon(Double.parseDouble(ubicacionDTO.getLon()));
                    objeto.setCalle(calle);
                    objetoRepository.save(objeto);
                }
            }
        }
    }
}
