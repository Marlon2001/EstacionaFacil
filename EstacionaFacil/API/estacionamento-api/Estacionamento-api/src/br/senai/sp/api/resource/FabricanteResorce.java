package br.senai.sp.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.api.model.Fabricante;
import br.senai.sp.api.repository.FabricanteRepository;

@RestController
@RequestMapping("/fabricante")
public class FabricanteResorce {
	
	@Autowired
	private FabricanteRepository fabricanteRepository;

	@GetMapping
	public List<Fabricante> getFabricantes() {
		return fabricanteRepository.findAll();
	}

	@PostMapping
	public List<Fabricante> setFabricante() {
		// cadastro de fabricante
		return null;
	}
}
