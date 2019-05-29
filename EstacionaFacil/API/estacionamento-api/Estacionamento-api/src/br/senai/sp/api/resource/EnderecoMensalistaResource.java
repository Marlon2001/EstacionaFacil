package br.senai.sp.api.resource;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.api.model.Cidade;
import br.senai.sp.api.model.Endereco;
import br.senai.sp.api.model.EnderecoMensalista;
import br.senai.sp.api.repository.CidadeRepository;
import br.senai.sp.api.repository.EnderecoMensalistaRepository;
import br.senai.sp.api.repository.EnderecoRepository;
import br.senai.sp.api.repository.EstadoRepository;
import br.senai.sp.api.repository.MensalistaRepository;

@RestController
@RequestMapping("/enderecoMensalista")
public class EnderecoMensalistaResource {
	
	@Autowired
	private EnderecoMensalistaRepository enderecoMensalistaRepository; 
	@Autowired 
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired 
	private MensalistaRepository mensalistaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	

	@GetMapping
	public List<EnderecoMensalista> getEnderecoMensalista(){
		return enderecoMensalistaRepository.findAll();
	}
	
	@PostMapping
	public EnderecoMensalista setEnderecoMensalista(@RequestBody EnderecoMensalista enderecoMensalista) {
		EnderecoMensalista enderecoMensalistaSalvo;
		Endereco enderecoSalvo = enderecoMensalista.getCodEndereco();
		enderecoSalvo = enderecoRepository.save(enderecoSalvo);
		
		Cidade cidade = enderecoMensalista.getCodEndereco().getCodCidade();
		cidade = cidadeRepository.findByCod(cidade.getCodCidade());
		Long codEstado = cidade.getCodEstado().getCodEstado();
		
		enderecoSalvo.getCodCidade().setCodEstado(
				estadoRepository.findByCod(codEstado)
		);
		
		enderecoSalvo.setCodCidade(cidade);;
		enderecoMensalistaSalvo = enderecoMensalistaRepository.save(enderecoMensalista);
		enderecoMensalistaSalvo.setCodMensalista(
				mensalistaRepository.findByCod(enderecoMensalistaSalvo.getCodMensalista().getCodMensalista()));
		
		enderecoMensalistaSalvo.setCodEndereco(
				enderecoRepository.findByCod(enderecoMensalistaSalvo.getCodEndereco().getCodEndereco())
		);
		
		return enderecoMensalistaSalvo;
	}
}
