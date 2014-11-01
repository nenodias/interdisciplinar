package br.org.fgp.view.core;

import java.awt.Font;

import javax.swing.JLabel;

public class JCabecalhoLabel extends JLabel {
	
	private static final long serialVersionUID = 1L;
	
	public JCabecalhoLabel(String texto) {
		super(texto);
		setFont(new Font(getFont().getName(), Font.BOLD, 14));
	}

}
