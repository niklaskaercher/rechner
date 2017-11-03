package me.niklas.rechner.listener;

import me.niklas.rechner.Rechner;
import me.niklas.rechner.currency.Currency;
import me.niklas.rechner.currency.CurrencyManager;
import me.niklas.rechner.gui.Window;
import me.niklas.rechner.gui.WindowManager;

public class SafeListener {
	
	public static void save() {
		
		Thread t = new Thread(new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				
				if(!Rechner.gui.txtName.getText().equals("") && !Rechner.gui.txtEditAmount.getText().equals("")) {
					try {
						double amount = Double.parseDouble(Rechner.gui.txtEditAmount.getText());
						String name = Rechner.gui.txtName.getText();
						
						if(Rechner.inEdit == null) {
							if(CurrencyManager.getByName(name) != null) {
								Rechner.gui.lblEditError.setText("Dieser Name ist bereits vergeben.");
								Thread.currentThread().stop();
							}
							Currency c = new Currency(name, amount, System.currentTimeMillis());
							c.writeIntoDataBase();
						} else {
							Rechner.inEdit.setName(name);
							Rechner.inEdit.setAmount(amount);
							Rechner.inEdit.setTime(System.currentTimeMillis());
							Rechner.inEdit.writeIntoDataBase();
						}
						
						Rechner.inEdit = null;
						
						Rechner.gui.txtName.setText("");
						Rechner.gui.txtEditAmount.setText("");
						
						Rechner.gui.lblEditError.setText("");
						Rechner.gui.lblEditState.setText("");
						
						Rechner.setModels();
						
						WindowManager.setWindow(Window.EDITMAIN);
						
						Thread.currentThread().stop();
					} catch(NumberFormatException e) {
						Rechner.gui.lblEditError.setText("Bitte gebe eine Zahl ein.");
						
						Thread.currentThread().stop();
					}
				} else {
					Rechner.gui.lblEditError.setText("Alle Felder m�ssen ausgefüllt sein.");
					
					Thread.currentThread().stop();
				}
				
			}
			
		});
		
		t.start();
	}
}
