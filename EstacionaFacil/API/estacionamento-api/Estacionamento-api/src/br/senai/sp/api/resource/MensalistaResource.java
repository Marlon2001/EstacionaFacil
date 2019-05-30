package br.senai.sp.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.api.model.Endereco;
import br.senai.sp.api.model.EnderecoMensalista;
import br.senai.sp.api.model.Mensalista;
import br.senai.sp.api.model.Telefone;
import br.senai.sp.api.model.TelefoneMensalista;
import br.senai.sp.api.model.Veiculo;
import br.senai.sp.api.repository.EnderecoMensalistaRepository;
import br.senai.sp.api.repository.EnderecoRepository;
import br.senai.sp.api.repository.MensalistaRepository;
import br.senai.sp.api.repository.TelefoneMensalistaRepository;
import br.senai.sp.api.repository.TelefoneRepository;
import br.senai.sp.api.repository.VeiculoRapository;

@RestController
@RequestMapping("/mensalista")
public class MensalistaResource {

	@Autowired
	private MensalistaRepository mensalistaRepository;
	@Autowired
	TelefoneMensalistaRepository telefoneMensalistaRepository;

	@Autowired
	TelefoneRepository telefoneRepository;

	@Autowired
	VeiculoRapository veiculoRapository;

	@Autowired
	EnderecoMensalistaRepository enderecoMensalistaRepository;

	@Autowired
	EnderecoRepository enderecoRepository;

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
