package com.matheusaraujo.estudoDeCaso.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService{
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendMail(SimpleMailMessage sm) {
		LOG.info("Simulando envio de e-mail");	
		LOG.info(sm.toString());
		LOG.info("Email enviado");
	}

}
