package br.org.fgp.view.core;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CopyOfJButtonExcluir extends JButton{
	
	private static final long serialVersionUID = -2971302897124405928L;
	
	private static final ClassLoader LOADER = CopyOfJButtonExcluir.class.getClassLoader();
	
	public CopyOfJButtonExcluir() {
		super("Adicionar");
		ImageIcon image = new ImageIcon(LOADER.getResource("imagens/adicionar.png"));
		setIcon(image);
	}
}
