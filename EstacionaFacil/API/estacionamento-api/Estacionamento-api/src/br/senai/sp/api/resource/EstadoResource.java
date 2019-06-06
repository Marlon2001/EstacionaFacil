package br.senai.sp.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.api.model.Estado;
import br.senai.sp.api.repository.EstadoRepository;

@RestController
@RequestMapping("/estado")
public class EstadoResource {

	@Autowired
	private EstadoRepository estadoRepository;

	@GetMapping
	public List<Estado> getEstados() {
		return estadoRepository.findAll();
	}
}
