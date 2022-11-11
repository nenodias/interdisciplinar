package br.org.fgp.annotations;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.org.fgp.model.enums.TipoUsuario;

@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permissao {
    TipoUsuario tipo() default TipoUsuario.ADMINISTRADOR;
}
