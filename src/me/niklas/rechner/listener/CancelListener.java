package me.niklas.rechner.listener;

import me.niklas.rechner.Rechner;
import me.niklas.rechner.gui.Window;
import me.niklas.rechner.gui.WindowManager;

public class CancelListener {

	public static void cancel() {
		
		Rechner.inEdit = null;
		
		Rechner.gui.lblEditError.setText("");
		Rechner.gui.lblEditState.setText("");
		Rechner.gui.txtName.setText("");
		Rechner.gui.txtEditAmount.setText("");
		
		WindowManager.setWindow(Window.EDITMAIN);
		
	}
}
