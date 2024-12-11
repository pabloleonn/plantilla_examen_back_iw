package com.ingweb.examen.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.ingweb.examen.model.dto.ObjetoDTO;
import com.ingweb.examen.service.*;
import io.swagger.v3.oas.annotations.Operation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ingweb.examen.model.dto.UsuarioDTO;
import com.ingweb.examen.model.dto.UsuarioFirebaseDTO;

import java.util.List;

//TODO
@RestController
@RequestMapping("/objeto")
public class ObjetoController {

    @Autowired
    private ObjetoService objetoService;

    @Autowired
    private CreateImageUseCase createImageUseCase;

    @Autowired
    private MapaService mapaService;

    @Autowired
    private FirebaseService firebaseService;

    @Autowired
    private UsuarioService usuarioService;

    // #########################################################
    // #                                                       #
    // #                    CONTROLADOR                        #
    // #                       OBJETO                          #
    // #                                                       #
    // #########################################################

    @Operation(description = "Devuelve todos los objetos")
    @GetMapping()
    public List<ObjetoDTO> getObjetos(){
        return objetoService.findAll();
    }

    @Operation(description = "Devuelve objeto por identificador")
    @GetMapping("/{id}")
    public ObjetoDTO getObjeto(@PathVariable ObjectId id){
        return objetoService.findById(id);
    }

    @Operation(description = "Crea un objeto")
    @PostMapping()
    public ObjetoDTO createObjeto(@RequestPart(value = "calle", required = false) String calle,
                                  @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        ObjetoDTO objetoDTO = new ObjetoDTO();
        ObjetoDTO dto = objetoService.createObjeto(objetoDTO);

        // ### AQUI SE MANEJA LA API DE MAPA Y LAS IMAGENES ###
        if(calle != null) {
            mapaService.updateUbicacion(dto, calle);
        }

        if(file != null) {
            dto = createImageUseCase.createImage(dto, file);
        }

        return dto;
    }

    @Operation(description = "Modifica un objeto por id")
    @PutMapping("/{id}")
    public ObjetoDTO updateObjeto(@PathVariable("id") ObjectId id, @RequestPart(value = "calle", required = false) String calle,
                                  @RequestParam(value = "file", required = false) MultipartFile file) throws Exception {
        ObjetoDTO objetoDTO = new ObjetoDTO();
        ObjetoDTO dto = objetoService.updateObjeto(id, objetoDTO);

        if(calle != null) {
            mapaService.updateUbicacion(dto, calle);
        }

        if(file != null) {
            dto = createImageUseCase.createImage(dto, file);
        }

        return dto;
    }

    @Operation(description = "Elimina un objeto por id")
    @DeleteMapping("/{id}")
    public void deleteObjeto(@PathVariable("id") ObjectId id){
        objetoService.deleteObjeto(id);
    }

    // #########################################################
    // #                                                       #
    // #                    CONTROLADOR                        #
    // #                   USUARIO/OAUTH                       #
    // #                                                       #
    // #########################################################

    @Operation(description = "Devuelve el usuario por token")
    @GetMapping("/token/{token}")
    public UsuarioDTO getUsuarioByToken(@PathVariable("token") String token) throws FirebaseAuthException {
        return firebaseService.getUsuarioByToken(token);
    }

    /*
    UsuarioFirebaseDTO recibe por el front ahora mismo:
        - accessToken
        - email
        - caducidad (en tipo timestamp) para transformarlo en el front hay que hacer new Date(caducidad)

    UsuarioFirebaseDTO a modificar en función de lo que pida el examen
     */
    @Operation(description = "Inicia sesión o registra mediante Google")
    @PostMapping("/inicio-google")
    public UsuarioDTO inicioGoogle(@RequestBody UsuarioFirebaseDTO usuarioFirebaseDTO) {
        return usuarioService.inicioORegistro(usuarioFirebaseDTO);
    }

}
