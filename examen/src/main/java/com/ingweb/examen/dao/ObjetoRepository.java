package com.ingweb.examen.dao;

import com.ingweb.examen.model.entity.Objeto;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//TODO
@Repository
public interface ObjetoRepository extends MongoRepository<Objeto, ObjectId> {
        /*
    `List<Entity> findByName(String name);
    List<Entity> findByNameAndNumber(String name, Integer number);
    List<Entity> findByNameOrNumber(String name, Integer number);
    List<Entity> findByNameContaining(String string);

    List<Entity> findByNumberBetween(Integer inicio, Integer fin);
    List<Entity> findByNumberGreaterThan(Integer number);

    List<Entity> findByNameOrderByNumberDesc(String name);
    List<Entity> findTop5ByName(String name);

    List<Entity> findByNumberIn(List<Integer> numbers);
    List<Entity> findByNameNot(String name);

    List<Entity> findByTimestampAfter(Date fecha);

    List<Entity> findByDireccionesDireccion(String direccion);

    @Query("{ 'contactos.nombre': ?0 }")
    List<Entity> findByNombreDeContacto(String nombreContacto);`
     */
}
