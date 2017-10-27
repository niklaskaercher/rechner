package me.niklas.rechner.currency;

import me.niklas.rechner.Rechner;
import me.niklas.rechner.util.RedisHelper;

public class CurrencyManager {

	public static Object[] getAll() {
		return RedisHelper.sgetAll("currency_set");
	}
	
	public static Currency getByName(String name) {
		String value = RedisHelper.get(name);
		
		if(value == null) {
			return null;
		}
		
		return Rechner.gson.fromJson(value, Currency.class);
	}
	
	public static double calculate(double amount, String currency1, String currency2) {
		Currency c1 = getByName(currency1);
		Currency c2 = getByName(currency2);
		
		double d = (amount * c1.getAmount()) / c2.getAmount();
		
		return d;
	}
}
