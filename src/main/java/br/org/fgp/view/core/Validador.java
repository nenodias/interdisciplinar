package br.org.fgp.view.core;

import java.util.Set;

import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.ValidatorFactory;

import br.org.fgp.core.MessagemUtil;

public class Validador<T> {
	
	public void validacaoCampos(T objeto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		javax.validation.Validator myValidate = factory.getValidator();
		Set<ConstraintViolation<T>> validacao = myValidate.validate(objeto);
		StringBuilder mensagem = new StringBuilder();
		boolean erro = false;
		for (ConstraintViolation<T> constraintViolation : validacao) {
			mensagem.append(constraintViolation.getMessage() ).append(MessagemUtil.BR);
			erro = true;
		}
		if(erro){
			JOptionPane.showMessageDialog(null, mensagem.toString(), MessagemUtil.ERRO, JOptionPane.ERROR_MESSAGE);
			throw new ValidationException( mensagem.toString() );
		}
	}

}
