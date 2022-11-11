package br.org.fgp.core.dao;

import java.util.List;


public interface Filtravel<T> {
    List<T> filtrarPorDescricao(String descricao);
}
