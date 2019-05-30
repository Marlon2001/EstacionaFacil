package br.senai.sp.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.api.model.Telefone;
import br.senai.sp.api.repository.TelefoneMensalistaRepository;
import br.senai.sp.api.repository.TelefoneRepository;

@RestController
@RequestMapping("/telefone")
public class TelefoneResource {
	
	@Autowired
	TelefoneRepository telefoneRepository;
	
	@Autowired
	TelefoneMensalistaRepository telefoneMensalistaRepository;
		
		
	@GetMapping
	public List<Telefone> getTelefones(){
		return telefoneRepository.findAll();
	}
	
	@DeleteMapping("{codTelefone}")
	public void deleteTelefone(@PathVariable Long codTelefone) {
		Telefone t = new Telefone();
		t.setCodTelefone(codTelefone);
		telefoneMensalistaRepository.deleteByCodTel(codTelefone);
		telefoneRepository.deleteById(codTelefone);
	}
	
	

}
