package com.fernandogranados.pruebaTecnica.services;

import com.fernandogranados.pruebaTecnica.models.EmpleadoModel;
import com.fernandogranados.pruebaTecnica.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    public ArrayList<EmpleadoModel> obtenerEmpleados(){
        return  (ArrayList<EmpleadoModel>)empleadoRepository.findAll();
    }

    public EmpleadoModel guardarEmpleado(EmpleadoModel empleado){
        return empleadoRepository.save(empleado);
    }
}
