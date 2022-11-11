package br.org.fgp.view.core;

import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;

public class Observador {

    private ActionListener listener;

    public void update(PropertyChangeEvent evt) {
        if (listener != null) {
            listener.actionPerformed(null);
        }
    }

    public ActionListener getListener() {
        return listener;
    }

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }

}
