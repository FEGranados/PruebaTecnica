package com.fernandogranados.pruebaTecnica.repositories;

import com.fernandogranados.pruebaTecnica.models.EmpleadoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends CrudRepository<EmpleadoModel, Long> {

}
