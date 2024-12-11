package com.ingweb.examen.service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.ingweb.examen.dao.UsuarioRepository;
import com.ingweb.examen.model.dto.UsuarioDTO;
import com.ingweb.examen.model.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService extends DTOService<UsuarioDTO, Usuario> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private FirebaseToken verifyToken(String idToken) throws FirebaseAuthException {
        // Verifica y decodifica el token
        return FirebaseAuth.getInstance().verifyIdToken(idToken);
    }

    public UsuarioDTO getUsuarioByToken(String idToken) throws FirebaseAuthException {
        // Verifica y decodifica el token
        FirebaseToken decodedToken = verifyToken(idToken);

        String email = decodedToken.getEmail();

        Usuario usuario = new Usuario();
        if (email != null) {
            // Busca el usuario por email
            usuario = usuarioRepository.findUsuarioByEmailIgnoreCase(email);
        }

        return usuario.toDTO();
    }
}
