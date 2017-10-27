package me.niklas.rechner.listener;

import me.niklas.rechner.Rechner;
import me.niklas.rechner.currency.Currency;
import me.niklas.rechner.currency.CurrencyManager;

public class EditListener {

	public static void edit() {
		String name = (String) Rechner.gui.editList.getSelectedValue();
		
		Currency c = CurrencyManager.getByName(name);
		
		Rechner.inEdit = c;
		
		Rechner.gui.txtName.setText(name);
		
		Rechner.gui.txtEditAmount.setText(Double.toString(c.getAmount()));
		
		Rechner.gui.lblEditState.setText("Bearbeiten: " + name);
	}
}
