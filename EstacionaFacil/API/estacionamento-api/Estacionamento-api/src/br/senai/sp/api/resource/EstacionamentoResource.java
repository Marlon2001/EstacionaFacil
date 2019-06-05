package br.senai.sp.api.resource;

import java.net.URI; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.senai.sp.api.model.Mensalista;
import br.senai.sp.api.model.Movimentacao;
import br.senai.sp.api.model.Precos;
import br.senai.sp.api.repository.EstacionamentoRepository;
import br.senai.sp.api.repository.PrecosRepository;
import br.senai.sp.api.repository.VeiculoRapository;
import br.senai.sp.utils.Cobrancas;
import br.senai.sp.utils.Date;

@RestController
@RequestMapping("/movimentacao")
public class EstacionamentoResource {
	
	@Autowired
	private EstacionamentoRepository estacionamentoRepository;
	@Autowired
	private PrecosRepository precosRepository;
	@Autowired
	private VeiculoRapository veiculoRaposytory;
	
	@GetMapping("/estacionados")
	public List<Movimentacao> getEstacionados() {
		return estacionamentoRepository.findByHoraSaidaIsNull();
	}
	
	@GetMapping("/placa/{placa}")
	public Movimentacao getByPlaca(@PathVariable String placa) {
		return estacionamentoRepository.findByPlaca(placa);
	}
	
	@GetMapping("/{cod_movimentacao}")
	public Optional<Movimentacao> getMovimentacao(@PathVariable Long cod_movimentacao) {
		return estacionamentoRepository.findById(cod_movimentacao);
	}
	
	
	
	@GetMapping("/orcamento/{cod_movimentacao}")
	public ResponseEntity<Movimentacao> gerarOrcamento(@PathVariable Long cod_movimentacao, HttpServletResponse response){
		Movimentacao movimentacao = estacionamentoRepository.findById(cod_movimentacao).get();	
		try {
			
			SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(st.parse(movimentacao.getDataEntrada()));
			List<String> dateRetorno = Date.getTimeDiff(calendar);
			
			// Calculando a quantidade de horas para calcular p preço a ser pago
			Long tempoPermanencia = Long.parseLong(dateRetorno.get(1));
			
			Precos precos = precosRepository.findByDataSaidaIsNull();
			Cobrancas cobranca = new Cobrancas();
			
			
			
			Double totalAPagar;
			
			//verificando o tipo da movimentação
			if(movimentacao.getTipo().equals("D")) {
				totalAPagar = precos.getValorDiaria();
			}else if(veiculoRaposytory.getVeiculosByMensalista(movimentacao.getPlaca()) > 0) {
				totalAPagar= 0.00;
			}else {
				totalAPagar = cobranca.getValorPagar(tempoPermanencia, precos);
			}
			
			movimentacao.setTempoPermanencia(Integer.parseInt(tempoPermanencia.toString()));
			movimentacao.setValorPago(totalAPagar);
			movimentacao.setDataSaida(dateRetorno.get(0));
			
			
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.path("/{cod_movimentacao}")
					.buildAndExpand(movimentacao.getCodMovimentacao())
					.toUri();
			response.addHeader("Location", uri.toASCIIString());
			
			return ResponseEntity.created(uri).body(movimentacao);
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	
	@PostMapping
	public ResponseEntity<Movimentacao> salvar(@RequestBody Movimentacao movimentacao, HttpServletResponse response){
		movimentacao.setDataEntrada(Date.dataAtual());
		Movimentacao movimentacaoSalva = estacionamentoRepository.save(movimentacao);
		
		//verificando o tipo da movimentação
		if(!movimentacao.getTipo().equals("D")) {
			if(veiculoRaposytory.getVeiculosByMensalista(movimentacao.getPlaca()) > 0) {
				movimentacao.setTipo("M");
			}else {
				movimentacao.setTipo("A");
			}
		}
		
		
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{cod_movimentacao}")
				.buildAndExpand(movimentacaoSalva.getCodMovimentacao())
				.toUri();
		response.addHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.ok(movimentacaoSalva);
	}
	
	@PutMapping("/saida/{cod_movimentacao}")
	public ResponseEntity<Movimentacao> gerarSaida(@PathVariable Long cod_movimentacao, HttpServletResponse response){
		Movimentacao movimentacaoSalva = estacionamentoRepository.findById(cod_movimentacao).get();	
		try {
			
			SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(st.parse(movimentacaoSalva.getDataEntrada()));
			List<String> dateRetorno = Date.getTimeDiff(calendar);
			
			// Calculando a quantidade de horas para calcular p preço a ser pago
			Long tempoPermanencia = Long.parseLong(dateRetorno.get(1));
			
			Precos precos = precosRepository.findByDataSaidaIsNull();
			Cobrancas cobranca = new Cobrancas();
			

			Double totalAPagar;
			
			//verificando o tipo da movimentação
			if(movimentacaoSalva.getTipo().equals("D")) {
				totalAPagar = precos.getValorDiaria();
			}else if(veiculoRaposytory.getVeiculosByMensalista(movimentacaoSalva.getPlaca()) > 0) {
				totalAPagar= 0.00;
			}else {
				totalAPagar = cobranca.getValorPagar(tempoPermanencia, precos);
			}
			
			
			movimentacaoSalva.setTempoPermanencia(Integer.parseInt(tempoPermanencia.toString()));
			movimentacaoSalva.setValorPago(totalAPagar);
			movimentacaoSalva.setDataSaida(dateRetorno.get(0));
			
			estacionamentoRepository.save(movimentacaoSalva);
			
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequestUri()
					.path("/{cod_movimentacao}")
					.buildAndExpand(movimentacaoSalva.getCodMovimentacao())
					.toUri();
			response.addHeader("Location", uri.toASCIIString());
			
			return ResponseEntity.created(uri).body(movimentacaoSalva);
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
