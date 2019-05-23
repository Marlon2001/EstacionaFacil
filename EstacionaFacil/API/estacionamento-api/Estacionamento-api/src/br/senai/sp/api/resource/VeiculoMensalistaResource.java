package br.senai.sp.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.api.model.VeiculoMensalista;
import br.senai.sp.api.repository.VeiculoMensalistaRepository;

@RestController
@RequestMapping("/veiculoMensalista")
public class VeiculoMensalistaResource {
	
	@Autowired
	private VeiculoMensalistaRepository veiculoMensalistaRepository;
	
	@GetMapping
	public List<VeiculoMensalista> getVeiculoMensalista(){
		return veiculoMensalistaRepository.findAll();
	}
}
