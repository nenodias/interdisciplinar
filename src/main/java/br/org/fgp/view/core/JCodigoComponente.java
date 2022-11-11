package br.org.fgp.view.core;

import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

public class JCodigoComponente extends JFormattedTextField {

    private static final long serialVersionUID = 5006998437108640905L;

    private Observador observer;

    public JCodigoComponente(NumberFormatter numberFormatter) {
        super(numberFormatter);
    }

    public Observador getObserver() {
        return observer;
    }

    public void setObserver(Observador observer) {
        this.observer = observer;
    }

    @Override
    public void setText(String t) {
        super.setText(t);
        if (observer != null) {
            observer.update(null);
        }
    }
}
