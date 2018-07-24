package com.pr1.consulsys.controller.medico;

import com.pr1.consulsys.exception.ResourceNotFoundException;
import com.pr1.consulsys.model.medico.Especialidad;
import com.pr1.consulsys.repository.medico.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EspecialidadController {

    @Autowired
    private EspecialidadRepository especialidadRepository;

    @GetMapping("/especialidades")
    public List<Especialidad> getEspecialidades() {
        return especialidadRepository.findAll();
    }

    @PostMapping("/especialidades")
    public Especialidad createEspecialidad(@Valid @RequestBody Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    @PutMapping("/especialidades/{especialidadId}")
    public Especialidad updateEspecialidad(@PathVariable Long especialidadId,
                                           @Valid @RequestBody Especialidad especialidadRequest) {
        return especialidadRepository.findById(especialidadId)
                .map(especialidad -> {
                    especialidad.setDescripcion(especialidadRequest.getDescripcion());
                    return especialidadRepository.save(especialidad);
                }).orElseThrow(() -> new ResourceNotFoundException("Especialidad not found with id " + especialidadId));
    }

    @DeleteMapping("/especialidades/{especialidadId}")
    public ResponseEntity<?> deleteEspecialidad(@PathVariable Long especialidadId) {
        return especialidadRepository.findById(especialidadId)
                .map(especialidad -> {
                    especialidadRepository.delete(especialidad);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Especialidad not found with id " + especialidadId));
    }

}
