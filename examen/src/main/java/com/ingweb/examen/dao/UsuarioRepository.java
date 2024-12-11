package com.ingweb.examen.dao;

import com.ingweb.examen.model.entity.Usuario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {
    public Usuario findUsuarioByNombreIgnoreCase(String email);

    public Usuario findUsuarioByEmailIgnoreCase(String email);
}
