package me.niklas.rechner;

import javax.swing.AbstractListModel;

import com.google.gson.Gson;

import me.niklas.rechner.currency.Currency;
import me.niklas.rechner.currency.CurrencyManager;
import me.niklas.rechner.gui.RechnerGui;
import me.niklas.rechner.gui.Window;
import me.niklas.rechner.gui.WindowManager;
import me.niklas.rechner.util.RedisHelper;

public class Rechner {
	
	public static RechnerGui gui;
	public static Gson gson = new Gson();
	public static Currency inEdit = null;

	public static void main(String[] args) {
		
		gui = new RechnerGui();
		gui.setVisible(true);
		
		WindowManager.setWindow(Window.MAIN);
		
		Thread t = new Thread(new Runnable() {


			@SuppressWarnings({ "deprecation" })
			@Override
			public void run() {
				
				connect();
				
				setModels();
				
				Thread.currentThread().stop();
			}
			
		});
		
		t.start();
	}
	
	public static void connect() {
		RedisHelper.connect("37.59.3.104", 1044, "whVV2Ab45fLoe4wg6rxAv68VNJnE2NQqhTcL9qgcC9YSy4wJanuyLhK3hpD77iGt");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
	public static void setModels() {
		Object[] obj = CurrencyManager.getAll();
		
		gui.firstCurrency.setModel(new AbstractListModel() {
			Object[] values = obj;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		gui.secondCurrency.setModel(new AbstractListModel() {
			Object[] values = obj;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		gui.editList.setModel(new AbstractListModel() {
			Object[] values = obj;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		gui.firstCurrency.setSelectedIndex(0);
		gui.secondCurrency.setSelectedIndex(0);
		gui.editList.setSelectedIndex(0);
	}

}
