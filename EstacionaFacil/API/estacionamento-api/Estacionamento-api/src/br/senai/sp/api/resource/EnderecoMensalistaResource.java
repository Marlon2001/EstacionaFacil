package br.senai.sp.api.resource;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.api.model.EnderecoMensalista;
import br.senai.sp.api.repository.EnderecoMensalistaRepository;

@RestController
@RequestMapping("/enderecoMensalista")
public class EnderecoMensalistaResource {
	
	@Autowired
	private EnderecoMensalistaRepository enderecoMensalistaRepository; 

	@GetMapping
	public List<EnderecoMensalista> getEnderecoMensalista(){
		return enderecoMensalistaRepository.findAll();
	}
}
