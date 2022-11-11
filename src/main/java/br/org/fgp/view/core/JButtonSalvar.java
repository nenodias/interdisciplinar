package br.org.fgp.view.core;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JButtonSalvar extends JButton {

    private static final long serialVersionUID = -2971302897124405928L;

    private static final ClassLoader LOADER = JButtonSalvar.class.getClassLoader();

    public JButtonSalvar() {
        super("Salvar");
        ImageIcon image = new ImageIcon(LOADER.getResource("imagens/salvar.png"));
        setIcon(image);
    }
}
