package br.org.fgp.view.core;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UILookAndFellManager {
	
	private static final String DEFAULT_FONT = "defaultFont";

	public static void init() {
		String laf = "";

		try {
			try {
				UIManager.LookAndFeelInfo[] info = UIManager
						.getInstalledLookAndFeels();

				if (laf.length() == 0) {
					laf = "NimbusLookAndFeel";
//					laf = "WindowsLookAndFeel";
//					laf = "GTKLookAndFeel";
				}

				for (UIManager.LookAndFeelInfo lookAndFeelInfo : info) {
					if (lookAndFeelInfo.getClassName().contains(laf)) {
						UIManager
								.setLookAndFeel(lookAndFeelInfo.getClassName());
						break;
					}
				}

			} catch (Exception ex) {
				UIManager.setLookAndFeel(UIManager
						.getSystemLookAndFeelClassName());
			}

		} catch (ClassNotFoundException ex) {
		} catch (InstantiationException ex) {
		} catch (IllegalAccessException ex) {
		} catch (UnsupportedLookAndFeelException ex) {
		}
		UIManager.getLookAndFeelDefaults().put(DEFAULT_FONT, new Font("Arial", Font.TRUETYPE_FONT, 12));
		UIManager.put("TextField.inactiveForeground", Color.BLACK); 
	}
	
}
