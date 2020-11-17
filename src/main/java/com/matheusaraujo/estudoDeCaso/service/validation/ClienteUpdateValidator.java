package com.matheusaraujo.estudoDeCaso.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.matheusaraujo.estudoDeCaso.DTO.ClienteDTO;
import com.matheusaraujo.estudoDeCaso.domain.Cliente;
import com.matheusaraujo.estudoDeCaso.repository.ClienteRepository;
import com.matheusaraujo.estudoDeCaso.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		// Pegando o id dentro da requisição
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Integer urlId = Integer.parseInt(map.get("id"));

		Cliente cliente = clienteRepository.findByEmail(objDto.getEmail());

		if (cliente != null) {
			if (!cliente.getId().equals(urlId)) {
				list.add(new FieldMessage("email", "E-mail já existe na base de dados"));
			}
		}

		// inclua os testes aqui, inserindo erros na lista

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
