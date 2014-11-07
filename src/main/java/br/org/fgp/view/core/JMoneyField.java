package br.org.fgp.view.core;

import javax.swing.JFormattedTextField;

import br.org.fgp.core.TelasUtils;

public class JMoneyField extends JFormattedTextField {

	private static final long serialVersionUID = 4112780987994107819L;

	public JMoneyField() {
		super(TelasUtils.getFormatadorDecimal());
	}
	
	@Override
	public String getText() {
		return super.getText().replaceAll(",", ".");
	}
	
}
