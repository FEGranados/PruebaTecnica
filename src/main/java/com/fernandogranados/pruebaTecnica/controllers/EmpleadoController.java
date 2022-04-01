package com.fernandogranados.pruebaTecnica.controllers;


import com.fernandogranados.pruebaTecnica.exception.ApiRequestException;
import com.fernandogranados.pruebaTecnica.models.EmpleadoModel;
import com.fernandogranados.pruebaTecnica.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @GetMapping
    public ArrayList<EmpleadoModel> obtenerEmpleados(){
        return  empleadoService.obtenerEmpleados();
    }

    @PostMapping
    public EmpleadoModel guardarEmpleado(@Valid @RequestBody EmpleadoModel empleado, Errors errors){
        if (errors.hasErrors()){trowError(errors);}
        if (!empleado.mayorEdad()){throw new ApiRequestException("El empleado debe ser mayor de edad");}
        if (!empleado.validarFechaNacimiento()){throw new ApiRequestException("El formato para la fecha de nacimiento es yyyy-MM-dd");}
        if (!empleado.validarFechaVinculacion()){throw new ApiRequestException("El formato para la fecha de vinculacion es yyyy-MM-dd");}
        return empleadoService.guardarEmpleado(empleado);
    }

    public void trowError(Errors errors){
        String message = "";
        int index= 0;
        for(ObjectError r: errors.getAllErrors()){
            if(index > 0){
                message += " | ";
            }
            message += String.format("Message: %s",r.getDefaultMessage());
        }
        throw new ApiRequestException(message);
    }
}
