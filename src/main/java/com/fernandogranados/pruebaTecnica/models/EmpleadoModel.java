package com.fernandogranados.pruebaTecnica.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;


@Entity
@Table(name = "empleado")
@Getter
@Setter
public class EmpleadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotEmpty(message = "El campo nombres no puede estar vacio. ")
    @Column(nullable = false)
    private String nombres;

    @NotEmpty(message = "El campo apellidos no puede estar vacio. ")
    @Column(nullable = false)
    private String apellidos;

    @NotEmpty(message = "El campo tipo de documento estar vacio. ")
    @Column(nullable = false)
    private String tipoDocumento;

    @NotEmpty(message = "El campo numero de documento no puede estar vacio. ")
    @Column(nullable = false)
    private String numeroDocumento;

    @NotNull(message = "El campo fecha de nacimiento no puede ir vacio.  ")
    @Column(nullable = false)
    private Date fechaNacimiento;

    @NotNull(message = "El campo fecha de vinculación no puede ir vacio.  ")
    @Column(nullable = false)
    private Date fechaVinculacion;

    @NotEmpty(message = "El campo cargo no puede estar vacio. ")
    @Column(nullable = false)
    private String cargo;

    @NotNull(message = "El campo salario no puede ir vacio.  ")
    @Column(nullable = false)
    private Double salario;


    @Transient
    private String tiempoVinculacion;
    @Transient
    private String edadActual;

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getFechaVinculacion() {
        return fechaVinculacion;
    }

    public String getTiempoVinculacion() {
        return tiempoActual("Tiempo vinculado a la compañia:", fechaVinculacion);
    }


    public String getEdadActual() {
        return tiempoActual("Edad actual:",fechaNacimiento);
    }

    public String tiempoActual(String formato, Date fecha){

        LocalDate actual = LocalDate.now();
        LocalDate fechaNacimiento = LocalDate.ofInstant(fecha.toInstant(), ZoneId.systemDefault());

        Period periodo = Period.between(fechaNacimiento, actual);

        return String.format("%s %s años, %s meses y %s días",formato,periodo.getYears(),
                periodo.getMonths(), periodo.getDays());
    }

    public boolean mayorEdad(){
        LocalDate actual = LocalDate.now();
        LocalDate fechaNacimiento = LocalDate.ofInstant(this.fechaNacimiento.toInstant(), ZoneId.systemDefault());
        Period tiempo = Period.between(fechaNacimiento, actual);
        if (tiempo.getYears() >=18){
            return true;
        }
        return false;
    }

    public boolean validarFechaNacimiento()
    {
        LocalDate fN = LocalDate.ofInstant(this.fechaNacimiento.toInstant(), ZoneId.systemDefault());
        if (fN.getYear() <=1700){
            return false;
        }
        return  true;
    }

    public boolean validarFechaVinculacion()
    {
        LocalDate fN = LocalDate.ofInstant(this.fechaVinculacion.toInstant(), ZoneId.systemDefault());
        if (fN.getYear() <=1700){
            return false;
        }
        return  true;
    }

}
