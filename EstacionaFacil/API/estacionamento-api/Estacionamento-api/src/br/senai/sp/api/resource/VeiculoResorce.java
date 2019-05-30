package br.senai.sp.api.resource;

import java.net.URI; 
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senai.sp.api.model.Fabricante;
import br.senai.sp.api.model.Mensalista;
import br.senai.sp.api.model.Veiculo;
import br.senai.sp.api.repository.FabricanteRepository;
import br.senai.sp.api.repository.MensalistaRepository;
import br.senai.sp.api.repository.VeiculoRapository;

@RestController
@RequestMapping("/veiculo")
public class VeiculoResorce {
	@Autowired
	private VeiculoRapository veiculoRapository;
	@Autowired
	FabricanteRepository fabricanteRepository;
	@Autowired
	MensalistaRepository mensalistaRepository;
	
	@GetMapping
	public List<Veiculo> getVeiculos(){
		return veiculoRapository.findAll(); 
	}
	
	@GetMapping("{codMensalista}")
	public List<Veiculo> getVeiculoPorMensalista(@PathVariable Long codMensalista){
		Mensalista m = new  Mensalista();
		m.setCodMensalista(codMensalista);
		return veiculoRapository.getVeiculosByCodMensalista(m);
	}
	
	@DeleteMapping("{codVeiculo}")
	public void deleteVeiculoo(@PathVariable Long codVeiculo) {
		veiculoRapository.deleteById(codVeiculo);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Veiculo> salvarVeiculo(@RequestBody Veiculo veiculo, HttpServletResponse response){
		Veiculo veiculoSalvo;
		Mensalista mensalistaSalvo;
		
		
		Fabricante fabricante;
		veiculoSalvo = veiculoRapository.save(veiculo);
		fabricante = fabricanteRepository.findByCod(veiculoSalvo.getFabricante().getCodFabricante());
		veiculoSalvo.setFabricante(fabricante);
		mensalistaSalvo  = mensalistaRepository.findByCod(veiculoSalvo.getCodMensalista().getCodMensalista());
		veiculoSalvo.setCodMensalista(mensalistaSalvo);
		
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{cod_veiculo}")
				.buildAndExpand(veiculoSalvo
				.getCodVeiculo()).toUri();
		
		response.addHeader("Location",uri.toASCIIString());
		return ResponseEntity.ok(veiculoSalvo);
	}
	
}
