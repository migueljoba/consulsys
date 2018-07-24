package com.pr1.consulsys.model.medico;

import com.pr1.consulsys.model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "especialidad_medica")
public class Especialidad extends AuditModel {
    @Id
    @GeneratedValue(generator = "especialidad_medica_generator")
    @SequenceGenerator(
            name = "especialidad_medica_generator",
            sequenceName = "espelcialidad_medica_sequence"
    )
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
