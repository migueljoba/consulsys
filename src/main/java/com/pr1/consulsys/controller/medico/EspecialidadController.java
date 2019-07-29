package com.pr1.consulsys.controller.medico;

import com.pr1.consulsys.exception.ResourceNotFoundException;
import com.pr1.consulsys.model.medico.Especialidad;
import com.pr1.consulsys.repository.medico.EspecialidadRepository;
import com.pr1.consulsys.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EspecialidadController {

	@Autowired
	private EspecialidadRepository especialidadRepository;

	@Autowired
	private EspecialidadService especialidadService;

	@GetMapping("/especialidades")
	public List<Especialidad> getEspecialidades() {
		return especialidadService.findAll();
	}

	@GetMapping("/especialidades/{especialidadId}")
	public ResponseEntity<Especialidad> createEspecialidad(@PathVariable Long especialidadId) {
		Especialidad especialidad = especialidadService.getById(especialidadId);

		if (especialidad == null) {
			// return new ResourceNotFoundException("Especialidad not found with id " + especialidadId);
		}

		ResponseEntity<Especialidad> response = new ResponseEntity<>(especialidad, HttpStatus.OK);
		return response;
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
