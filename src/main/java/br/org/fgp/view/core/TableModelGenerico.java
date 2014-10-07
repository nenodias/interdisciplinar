package br.org.fgp.view.core;

import java.lang.reflect.Method;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.AbstractTableModel;

import br.org.fgp.view.annotations.Pesquisa;

public class TableModelGenerico extends AbstractTableModel {
	private static final String EXCLUIR_LABEL = "Excluir";
	private static final String EDITAR_LABEL = "Editar";
	private static final String VAZIO = "";
	private static final long serialVersionUID = -6777048973027518361L;
	private final List<?> lista;
	private final Class<?> classe;
	private Integer countadorColunas = 0;

	public TableModelGenerico(List<?> lista, Class classe) {
		this.lista = lista;
		this.classe = classe;
		
	}

	@Override
	public int getRowCount() {
		return lista.size();
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
		return count +2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == countadorColunas){
			return EDITAR_LABEL;
		}else if(columnIndex == countadorColunas+1){
			return EXCLUIR_LABEL;
		}else{
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
		return VAZIO;
	}

	public Integer getCountadorColunas() {
		return countadorColunas;
	}
	
	
}
