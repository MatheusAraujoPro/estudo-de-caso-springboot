package com.matheusaraujo.estudoDeCaso.service;


import org.springframework.mail.SimpleMailMessage;

import com.matheusaraujo.estudoDeCaso.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendMail(SimpleMailMessage sm);

}
