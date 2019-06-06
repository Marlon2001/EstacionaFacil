package br.senai.sp.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.api.model.Telefone;
import br.senai.sp.api.repository.TelefoneMensalistaRepository;
import br.senai.sp.api.repository.TelefoneRepository;

@RestController
@RequestMapping("/telefone")
public class TelefoneResource {

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Autowired
	private TelefoneMensalistaRepository telefoneMensalistaRepository;

	@GetMapping
	public List<Telefone> getTelefones() {
		return telefoneRepository.findAll();
	}

	@DeleteMapping("/{codTelefone}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTelefone(@PathVariable Long codTelefone) {
		telefoneMensalistaRepository.deleteByCodTel(codTelefone);
		telefoneRepository.deleteById(codTelefone);
	}
}
