package br.org.fgp.view.core;

import java.lang.reflect.Method;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.org.fgp.view.annotations.Pesquisa;

public class TableModelGenerico extends AbstractTableModel {
	private static final long serialVersionUID = -6777048973027518361L;
	private final List<?> lista;
	private final Class<?> classe;

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
		return count;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
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
		return null;
	}
	
	
}
