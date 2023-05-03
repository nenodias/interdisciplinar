package br.org.fgp.view.core;

import java.util.Set;

import javax.swing.JOptionPane;

import br.org.fgp.core.MessagemUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ValidationException;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

public class Validador<T> {

    public void validacaoCampos(T objeto) {
        ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> validacao = validator.validate(objeto);
        StringBuilder mensagem = new StringBuilder();
        boolean erro = false;
        for (ConstraintViolation<T> constraintViolation : validacao) {
            mensagem.append(constraintViolation.getMessage()).append(MessagemUtil.BR);
            erro = true;
        }
        if (erro) {
            JOptionPane.showMessageDialog(null, mensagem.toString(), MessagemUtil.ERRO, JOptionPane.ERROR_MESSAGE);
            throw new ValidationException(mensagem.toString());
        }
    }

}
