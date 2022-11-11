package br.org.fgp.core;

public class InterdisciplinarStringUtil {

    public static String upperFirstWord(String texto) {
        String retorno = null;
        if (texto != null && texto.length() > 1) {
            String resto = texto.substring(1);
            String first = texto.substring(0, 1);
            retorno = first.toUpperCase().concat(resto);
        } else if (texto != null && texto.length() == 1) {
            retorno = texto.toUpperCase();
        }
        return retorno;
    }

}
