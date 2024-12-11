package com.ingweb.examen.service;

import com.ingweb.examen.dao.ObjetoRepository;
import com.ingweb.examen.model.dto.ObjetoDTO;
import com.ingweb.examen.model.entity.Objeto;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO
@Service
public class ObjetoService extends DTOService<ObjetoDTO, Objeto> {

    @Autowired
    private ObjetoRepository objetoRepository;

    public List<ObjetoDTO> findAll() {
        return entidadesADTO(objetoRepository.findAll());
    }

    public ObjetoDTO findById(ObjectId id) {
        return objetoRepository.findById(id).get().toDTO();
    }

    public ObjetoDTO createObjeto(ObjetoDTO objetoDTO) {
        Objeto objeto = new Objeto();
        //TODO
        return objetoRepository.save(objeto).toDTO();
    }

    public ObjetoDTO updateObjeto(ObjectId id, ObjetoDTO objetoDTO) {
        Objeto objeto = objetoRepository.findById(id).get();
       //TODO
        return objetoRepository.save(objeto).toDTO();
    }

    public void deleteObjeto(ObjectId id) {
        objetoRepository.deleteById(id);
    }
}

