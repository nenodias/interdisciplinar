package br.org.fgp.view.core;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JButtonExcluir extends JButton{
	
	private static final long serialVersionUID = -2971302897124405928L;
	
	private static final ClassLoader LOADER = JButtonExcluir.class.getClassLoader();
	
	public JButtonExcluir() {
		super("Excluir");
		ImageIcon image = new ImageIcon(LOADER.getResource("imagens/excluir.png"));
		setIcon(image);
	}
}
