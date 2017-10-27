package me.niklas.rechner.listener;

import me.niklas.rechner.Rechner;
import me.niklas.rechner.currency.Currency;
import me.niklas.rechner.currency.CurrencyManager;

public class DeleteListener {

	public static void delete() {
		Thread t = new Thread(new Runnable() {

			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				Currency c = CurrencyManager.getByName((String) Rechner.gui.editList.getSelectedValue());
				
				c.delete();
				
				Rechner.setModels();
				
				Thread.currentThread().stop();
			}
			
		});
		
		t.start();
	}
	
}
