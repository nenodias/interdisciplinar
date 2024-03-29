package br.org.fgp.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class InterdisciplinarReflectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterdisciplinarReflectionUtil.class);
    private static final String LITERAL_GET = "get";
    private static final String LITERAL_SET = "set";

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<Field> getAtributoComAnotacao(Class clazz, Class anotacao) {
        List<Field> retorno = new ArrayList<Field>();
        Field[] camposDeclarados = clazz.getDeclaredFields();
        for (Field campo : camposDeclarados) {
            if (campo.isAnnotationPresent(anotacao)) {
                retorno.add(campo);
            }
        }
        return retorno;
    }

    @SuppressWarnings({"all"})
    public static Method getMetodoGet(Class clazz, Field campo) {
        String campoUpperFirstWord = InterdisciplinarStringUtil.upperFirstWord(campo.getName());
        String nomeMetodo = LITERAL_GET.concat(campoUpperFirstWord);
        Method metodo = null;
        try {
            metodo = clazz.getMethod(nomeMetodo, null);
        } catch (Exception e) {
            LOGGER.info("Metodo não encontrado", e);
        }
        return metodo;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Method getMetodoSet(Class clazz, Field campo) {
        String campoUpperFirstWord = InterdisciplinarStringUtil.upperFirstWord(campo.getName());
        String nomeMetodo = LITERAL_SET.concat(campoUpperFirstWord);
        Method metodo = null;
        try {
            metodo = clazz.getMethod(nomeMetodo, campo.getType());
        } catch (NoSuchMethodException | SecurityException e) {
            LOGGER.info("Metodo não encontrado", e);
        }
        return metodo;
    }

}
