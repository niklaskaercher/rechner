package me.niklas.rechner.listener;

import me.niklas.rechner.Rechner;
import me.niklas.rechner.currency.CurrencyManager;

public class CalculateListener {

	public static void calculate() {
		
		Thread t = new Thread(new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				
				double amount = 0;
				
				try {
					amount = Double.parseDouble(Rechner.gui.txtAmount.getText());
				} catch(NumberFormatException e) {
					Rechner.gui.lblOutput.setText("Bitte gebe eine gültige Zahl ein.");
					Thread.currentThread().stop();
				}
				
				String currency1 = (String) Rechner.gui.firstCurrency.getSelectedValue();
				String currency2 = (String) Rechner.gui.secondCurrency.getSelectedValue();
				
				double output = CurrencyManager.calculate(amount, currency1, currency2);
				
				Rechner.gui.lblOutput.setText(Double.toString(amount) + " " + currency1 + " ist " + Double.toString(output) + " " + currency2 + " wert.");
			}
			
		});
		
		t.start();
	}
}
