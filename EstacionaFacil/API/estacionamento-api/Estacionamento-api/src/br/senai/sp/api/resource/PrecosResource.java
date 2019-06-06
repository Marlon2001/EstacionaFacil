package br.senai.sp.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import br.senai.sp.api.repository.PrecosRepository;
import br.senai.sp.utils.Date;
import br.senai.sp.api.model.Precos;

@RestController
@RequestMapping("/precos")
public class PrecosResource {

	@Autowired
	private PrecosRepository precosRepository;

	@GetMapping
	public List<Precos> getPrecos() {
		return precosRepository.findAll();
	}

	@GetMapping("/precoVigente")
	public Precos getPrecoVigente() {
		return precosRepository.findByDataSaidaIsNull();
	}

	// cadastrar preco recebe um json com preco e salva ele no banco
	@PostMapping("/cadastroPreco")
	public ResponseEntity<Precos> cadastrarPreco(@RequestBody Precos preco, HttpServletResponse response) {

		Precos precoAtual = precosRepository.findByDataSaidaIsNull();

		Precos precoSalvar = precosRepository.save(preco);
		String dataAtual = Date.dataAtual();
		precoAtual.setDataSaida(dataAtual);
		precosRepository.save(precoAtual);

		// para retornar o preco cadastrado agora criamos uma uri e setamos o retorno do
		// preco cadastrado
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{cod_preco}").buildAndExpand(precoSalvar)
				.toUri();
		response.addHeader("Location", uri.toASCIIString());
		return ResponseEntity.ok(preco);
	}
}
