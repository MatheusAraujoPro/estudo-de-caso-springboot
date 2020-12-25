package com.matheusaraujo.estudoDeCaso.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.matheusaraujo.estudoDeCaso.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public  void preencherPagamentoComBoleto(PagamentoComBoleto boleto, Date instante) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instante);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		boleto.setDataVencimento(cal.getTime());	
		
	}
	

}
