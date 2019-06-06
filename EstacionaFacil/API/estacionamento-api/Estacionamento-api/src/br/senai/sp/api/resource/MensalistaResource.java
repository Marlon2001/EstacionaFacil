package br.senai.sp.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.api.model.Mensalista;
import br.senai.sp.api.repository.MensalistaRepository;

@RestController
@RequestMapping("/mensalista")
public class MensalistaResource {

	@Autowired
	private MensalistaRepository mensalistaRepository;

	@GetMapping
	public List<Mensalista> getMensalista() {
		return mensalistaRepository.findAll();
	}

	@PostMapping
	public Mensalista setMensalista(@RequestBody Mensalista mensalista) {
		Mensalista mensalistaSalvo;
		mensalistaSalvo = mensalistaRepository.save(mensalista);
		return mensalistaSalvo;
	}

}
