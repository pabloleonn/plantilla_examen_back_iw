package com.ingweb.examen.service;

import com.ingweb.examen.dao.ObjetoRepository;
import com.ingweb.examen.model.dto.ObjetoDTO;
import com.ingweb.examen.model.entity.Objeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CreateImageUseCase {

    @Autowired
    StorageService storageService;

    @Autowired
    private ObjetoRepository objetoRepository;

    @Transactional
    public ObjetoDTO createImage(ObjetoDTO dto, MultipartFile file) throws Exception {
        Objeto objeto = objetoRepository.findById(dto.get_id()).get();
        String filename = storageService.getFilename(file);

        String url = storageService.uploadImage(file, filename);
        objeto.setUrl(url);

        return objetoRepository.save(objeto).toDTO();
    }

}
