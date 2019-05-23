package br.senai.sp.utils;

import br.senai.sp.api.model.Precos;

public class Cobrancas {
	

	public Double getValorPagar(Long tempoPermanencia, Precos preco) {
		Double totalAPagar = 0.00;
		
		Double valor_hora_1 = preco.getValorHora1();
		Double valor_demais_horas = preco.getValorDemaisHoras();
		Integer cont = 0;
		
		// Se a qtde de horas for maior que 0, siginifica que o cliente estacionou o carro 
		// por pelo menos 1 hora
		while(tempoPermanencia > 5) {
			if(cont == 0) {
				totalAPagar += valor_hora_1;
				tempoPermanencia -= 60;
				cont ++;
			} else {
				totalAPagar += valor_demais_horas;
				tempoPermanencia -= 60;
				cont++;
			}
		} 
		System.out.println("cobrado "+cont+" horas estacionadas");
		
		return totalAPagar;
	}

}
