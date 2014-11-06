package br.org.fgp.view.core;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JButtonEditar extends JButton{
	
	private static final long serialVersionUID = -2971302897124405928L;
	
	private static final ClassLoader LOADER = JButtonEditar.class.getClassLoader();
	
	public JButtonEditar() {
		super("Editar");
		ImageIcon image = new ImageIcon(LOADER.getResource("imagens/editar.png"));
		setIcon(image);
	}
}
