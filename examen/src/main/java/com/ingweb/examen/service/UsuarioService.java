package com.ingweb.examen.service;

import com.ingweb.examen.dao.UsuarioRepository;
import com.ingweb.examen.model.dto.UsuarioDTO;
import com.ingweb.examen.model.dto.UsuarioFirebaseDTO;
import com.ingweb.examen.model.entity.Usuario;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class UsuarioService extends DTOService<UsuarioDTO, Usuario> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO create(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTimestamp(LocalDate.now());
        usuario.setCaducidad(usuarioDTO.getCaducidad());
        usuarioRepository.save(usuario);
        return usuario.toDTO();
    }

    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return entidadesADTO(usuarios);
    }

    public UsuarioDTO findUsuarioById(ObjectId id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        return usuario != null ? usuario.toDTO() : null;
    }

    public UsuarioDTO findByNombre(String nombre) {
        return usuarioRepository.findUsuarioByNombreIgnoreCase(nombre).toDTO();
    }

    public UsuarioDTO findByEmail(String email) {
        return usuarioRepository.findUsuarioByEmailIgnoreCase(email).toDTO();
    }

    public UsuarioDTO update(ObjectId id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            return null;
        }
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setTimestamp(usuarioDTO.getTimestamp());
        usuario.setCaducidad(usuarioDTO.getCaducidad());
        usuarioRepository.save(usuario);
        return usuario.toDTO();
    }

    public void delete(ObjectId id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO inicioORegistro(UsuarioFirebaseDTO usuarioFirebaseDTO) {
        Usuario usuario = usuarioRepository.findUsuarioByEmailIgnoreCase(usuarioFirebaseDTO.getEmail());

        return usuario != null ? handleInicio(usuarioFirebaseDTO) : handleRegistro(usuarioFirebaseDTO);
    }

    private UsuarioDTO handleRegistro(UsuarioFirebaseDTO usuarioFirebaseDTO) {
        System.out.println("Registrando usuario");
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioFirebaseDTO.getEmail());
        usuario.setTimestamp(LocalDate.now());
        usuario.setCaducidad(usuarioFirebaseDTO.getCaducidad());
        usuarioRepository.save(usuario);
        return usuario.toDTO();
    }

    private UsuarioDTO handleInicio(UsuarioFirebaseDTO usuarioFirebaseDTO) {
        System.out.println("Iniciando sesión");
        Usuario usuario = usuarioRepository.findUsuarioByEmailIgnoreCase(usuarioFirebaseDTO.getEmail());
        return usuario.toDTO();
    }

}
