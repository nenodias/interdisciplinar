package br.org.fgp.view.core;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JButtonAdicionar extends JButton{
	
	private static final long serialVersionUID = -2971302897124405928L;
	
	private static final ClassLoader LOADER = JButtonAdicionar.class.getClassLoader();
	
	public JButtonAdicionar() {
		super("Adicionar");
		ImageIcon image = new ImageIcon(LOADER.getResource("imagens/adicionar.png"));
		setIcon(image);
	}
}
