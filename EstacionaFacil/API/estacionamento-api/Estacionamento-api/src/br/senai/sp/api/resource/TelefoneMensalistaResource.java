package br.senai.sp.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.log.Log;

import br.senai.sp.api.model.Mensalista;
import br.senai.sp.api.model.Telefone;
import br.senai.sp.api.model.TelefoneMensalista;
import br.senai.sp.api.repository.MensalistaRepository;
import br.senai.sp.api.repository.TelefoneMensalistaRepository;
import br.senai.sp.api.repository.TelefoneRepository;

@RestController
@RequestMapping("/telefoneMensalista")
public class TelefoneMensalistaResource {
	@Autowired
	TelefoneMensalistaRepository telefoneMensalistaRepository;
	@Autowired
	TelefoneRepository telefoneRepository;
	@Autowired 
	MensalistaRepository mensalistaRepository;
	
	@GetMapping
	public List<TelefoneMensalista> getTelefonesMensalistas(){
		return telefoneMensalistaRepository.findAll(); 
	}
	
	
	
	@GetMapping("{codMensalista}")
	public List<TelefoneMensalista> getTelefonesPorMensalista(@PathVariable Long codMensalista){
		Mensalista m = new Mensalista();
		m.setCodMensalista(codMensalista);
		
		return telefoneMensalistaRepository.findByCodMensalista(m);
	}
	
	@PostMapping
	public TelefoneMensalista setTelefoneMensalista(@RequestBody TelefoneMensalista telefoneMensalista) {
		TelefoneMensalista telefoneMensalistaSalvo;
		
		Telefone telefoneSalvo = telefoneMensalista.getCodTelefone();
		
		telefoneSalvo = telefoneRepository.save(telefoneSalvo);
		telefoneMensalista.setCodTelefone(telefoneSalvo);
		
		telefoneMensalistaSalvo = telefoneMensalistaRepository.save(telefoneMensalista);
		telefoneMensalistaSalvo.setCodMensalista(mensalistaRepository.findByCod(telefoneMensalistaSalvo.getCodMensalista().getCodMensalista()));
		telefoneMensalistaSalvo.setCodTelefone(
				telefoneRepository.findByCod(telefoneMensalistaSalvo.getCodTelefone().getCodTelefone())
		);
		
		return telefoneMensalistaSalvo;
	}
	 
	
	
	
	

}
