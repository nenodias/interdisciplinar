package br.org.fgp.view.core;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class JButtonOk extends JButton {

    private static final long serialVersionUID = -2971302897124405928L;

    private static final ClassLoader LOADER = JButtonOk.class.getClassLoader();

    public JButtonOk() {
        super("Ok");
        ImageIcon image = new ImageIcon(LOADER.getResource("imagens/ok.png"));
        setIcon(image);
    }
}
