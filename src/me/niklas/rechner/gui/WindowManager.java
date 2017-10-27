package me.niklas.rechner.gui;

import me.niklas.rechner.Rechner;

public class WindowManager {

	public static void setWindow(Window w) {
		
		switch(w) {
		case MAIN: 
			Rechner.gui.mainPane.setVisible(true);
			Rechner.gui.editPane.setVisible(false);
			Rechner.gui.editMainPane.setVisible(false);
			Rechner.gui.errorPane.setVisible(false);
			break;
		case EDITMAIN:
			Rechner.gui.mainPane.setVisible(false);
			Rechner.gui.editPane.setVisible(true);
			Rechner.gui.editMainPane.setVisible(false);
			Rechner.gui.errorPane.setVisible(false);
			break;
		case EDIT:
			Rechner.gui.mainPane.setVisible(false);
			Rechner.gui.editPane.setVisible(false);
			Rechner.gui.editMainPane.setVisible(true);
			Rechner.gui.errorPane.setVisible(false);
			break;
		case ERROR:
			Rechner.gui.mainPane.setVisible(false);
			Rechner.gui.editPane.setVisible(false);
			Rechner.gui.editMainPane.setVisible(false);
			Rechner.gui.errorPane.setVisible(true);
			break;
		default: 
			break;
		}
		
	}
}
