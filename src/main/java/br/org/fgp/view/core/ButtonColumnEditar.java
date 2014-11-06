package br.org.fgp.view.core;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTable;

public class ButtonColumnEditar extends ButtonColumn {
	
	public ButtonColumnEditar(JTable table, Action action, int column) {
		super(table, action, column);
	}

	private static final long serialVersionUID = -142864966177167939L;

	@Override
	protected JButton getButton() {
		return new JButtonEditar();
	}
}
