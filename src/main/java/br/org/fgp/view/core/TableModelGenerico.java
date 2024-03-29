package br.org.fgp.view.core;

import java.lang.reflect.Method;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang.StringUtils;

import br.org.fgp.view.annotations.Pesquisa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableModelGenerico<T> extends AbstractTableModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableModelGenerico.class);
    private static final String EXCLUIR_LABEL = "Excluir";
    private static final String EDITAR_LABEL = "Editar";
    private static final long serialVersionUID = -6777048973027518361L;
    private final List<T> lista;
    private final Class<T> classe;
    private Integer countadorColunas = 0;
    private Boolean botoes = true;

    public TableModelGenerico(List<T> lista, Class<T> classe, Boolean botoes) {
        this.lista = lista;
        this.classe = classe;
        this.botoes = botoes;
    }

    public TableModelGenerico(List<T> lista, Class<T> classe) {
        this.lista = lista;
        this.classe = classe;
    }

    @Override
    public int getRowCount() {
        return (lista != null ? lista.size() : 0);
    }

    @Override
    public int getColumnCount() {
        int count = 0;
        for (Method metodo : classe.getMethods()) {
            if (metodo.isAnnotationPresent(Pesquisa.class)) {
                count++;
            }
        }
        //Adicionando Espaço Botão Editar e Excluir
        countadorColunas = count;
        if (botoes) {
            count += 2;
        }
        return count;
    }

    @SuppressWarnings({"all"})
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == countadorColunas && botoes) {
            return EDITAR_LABEL;
        } else if (columnIndex == countadorColunas + 1 && botoes) {
            return EXCLUIR_LABEL;
        } else {
            for (Method metodo : classe.getMethods()) {
                Pesquisa annotation = metodo.getAnnotation(Pesquisa.class);
                if (metodo.isAnnotationPresent(Pesquisa.class) && annotation.posicao() == columnIndex) {
                    try {
                        return String.format(annotation.pattern(), metodo.invoke(lista.get(rowIndex), null));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        for (Method metodo : classe.getMethods()) {
            Pesquisa annotation = metodo.getAnnotation(Pesquisa.class);
            if (metodo.isAnnotationPresent(Pesquisa.class) && annotation.posicao() == column) {
                return annotation.nome();
            }
        }
        return StringUtils.EMPTY;
    }

    public Integer getCountadorColunas() {
        return countadorColunas;
    }

    public List<T> getLista() {
        return lista;
    }

}
