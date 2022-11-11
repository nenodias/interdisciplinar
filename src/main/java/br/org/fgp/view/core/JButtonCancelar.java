package br.org.fgp.view.core;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JButtonCancelar extends JButton {

    private static final long serialVersionUID = -2971302897124405928L;

    private static final ClassLoader LOADER = JButtonCancelar.class.getClassLoader();

    public JButtonCancelar() {
        super("Cancelar");
        ImageIcon image = new ImageIcon(LOADER.getResource("imagens/cancelar.png"));
        setIcon(image);
    }
}
