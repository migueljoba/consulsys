package com.pr1.consulsys.service;

import com.pr1.consulsys.model.medico.Especialidad;
import com.pr1.consulsys.repository.medico.EspecialidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("especialidadService")
public class EspecialidadService {

	@Autowired
	private EspecialidadRepository especialidadRepository;

	public List<Especialidad> findAll() {
		return especialidadRepository.findAll();
	}

	public Especialidad getById(Long especialidadId) {
		Optional<Especialidad> especialidadOpt = especialidadRepository.findById(especialidadId);

		Especialidad especialidad = null;

		if (especialidadOpt.isPresent()) {
			especialidad = especialidadOpt.get();
		}

		return especialidad;
	}

}
