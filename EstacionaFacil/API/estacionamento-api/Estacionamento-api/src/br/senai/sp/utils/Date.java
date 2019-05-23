package br.senai.sp.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Date {
	
	public static List<String> getTimeDiff(Calendar horaEntrada) {
		
		ArrayList<String> dateRetorno = new ArrayList<String>();
		
		// Criando um calendar com a hora de entrada
		GregorianCalendar calendarioEntrada = new GregorianCalendar(
			horaEntrada.get(Calendar.YEAR),
			horaEntrada.get(Calendar.MONTH), 
			horaEntrada.get(Calendar.DAY_OF_MONTH),
			horaEntrada.get(Calendar.HOUR_OF_DAY),
			horaEntrada.get(Calendar.MINUTE),
			horaEntrada.get(Calendar.SECOND));
		
		// Criando um calendar com a hora atual
		Calendar horaSaida = Calendar.getInstance();
		GregorianCalendar calendarioSaida = new GregorianCalendar(
			horaSaida.get(Calendar.YEAR),
			horaSaida.get(Calendar.MONTH),
			horaSaida.get(Calendar.DAY_OF_MONTH),
			horaSaida.get(Calendar.HOUR_OF_DAY),
			horaSaida.get(Calendar.MINUTE),
			horaSaida.get(Calendar.SECOND));
		
		long dateDiff = calendarioEntrada.getTimeInMillis() - calendarioSaida.getTimeInMillis();
		long minutos = dateDiff / (1000 * 60);
		
		// Guardando em um List a hora de saida e o tempo de permanencia
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		
		System.out.println(dt.format(calendarioSaida.getTime()));
		
		dateRetorno.add(String.valueOf(dt.format(calendarioSaida.getTime())));
		dateRetorno.add(String.valueOf(Math.abs(minutos)));
		
		return dateRetorno;
	}
	
	public static String dataAtual() {
		// Criando um calendar com a hora atual
		Calendar calendarHora = Calendar.getInstance();
		GregorianCalendar calendarioSaida = new GregorianCalendar(
			calendarHora.get(Calendar.YEAR),
			calendarHora.get(Calendar.MONTH),
			calendarHora.get(Calendar.DAY_OF_MONTH),
			calendarHora.get(Calendar.HOUR_OF_DAY),
			calendarHora.get(Calendar.MINUTE),
			calendarHora.get(Calendar.SECOND));
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
		String horaAtual = dt.format(calendarioSaida.getTime());
		return horaAtual;
	}
}
