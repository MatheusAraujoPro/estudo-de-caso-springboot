package com.matheusaraujo.estudoDeCaso.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.matheusaraujo.estudoDeCaso.DTO.ClienteNewDTO;
import com.matheusaraujo.estudoDeCaso.domain.Cliente;
import com.matheusaraujo.estudoDeCaso.domain.enuns.TipoCliente;
import com.matheusaraujo.estudoDeCaso.repository.ClienteRepository;
import com.matheusaraujo.estudoDeCaso.resources.exception.FieldMessage;
import com.matheusaraujo.estudoDeCaso.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo())
				&& !BR.isValidCPF(objDto.getCpfOucnpj())) {
			
			list.add(new FieldMessage("cpfOucnpj", "CPF inválido"));
			
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo())
				&& !BR.isValidCNPJ(objDto.getCpfOucnpj())) {
			
			list.add(new FieldMessage("cpfOucnpj", "CNPJ inválido"));
			
		}
		
		Cliente cliente = clienteRepository.findByEmail(objDto.getEmail());
		
		if(cliente != null)
			list.add(new FieldMessage("email", "E-mail já existe na base de dados"));


		// inclua os testes aqui, inserindo erros na lista

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
