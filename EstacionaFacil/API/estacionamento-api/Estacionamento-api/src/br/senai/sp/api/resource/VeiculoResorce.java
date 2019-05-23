package br.senai.sp.api.resource;

import java.net.URI; 
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senai.sp.api.model.Fabricante;
import br.senai.sp.api.model.Veiculo;
import br.senai.sp.api.repository.FabricanteRepository;
import br.senai.sp.api.repository.VeiculoRepository;

@RestController
@RequestMapping("/veiculo")
public class VeiculoResorce {
	@Autowired
	private VeiculoRepository veiculoRapository;
	@Autowired
	FabricanteRepository fabricanteRepository;
	
	@GetMapping
	public List<Veiculo> getVeiculos(){
		return veiculoRapository.findAll(); 
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Veiculo> salvarVeiculo(@RequestBody Veiculo veiculo, HttpServletResponse response){
		Veiculo veiculoSalvo;
		
		Fabricante fabricante;
		veiculoSalvo = veiculoRapository.save(veiculo);
		fabricante = fabricanteRepository.findByCod(veiculoSalvo.getCodFabricante().getCodFabricante());
		veiculoSalvo.setCodFabricante(fabricante);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{cod_veiculo}")
				.buildAndExpand(veiculoSalvo
				.getCodVeiculo()).toUri();
		
		response.addHeader("Location",uri.toASCIIString());
		return ResponseEntity.ok(veiculoSalvo);
	}
	
}
