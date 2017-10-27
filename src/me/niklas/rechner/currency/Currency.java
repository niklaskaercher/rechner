package me.niklas.rechner.currency;

import me.niklas.rechner.Rechner;
import me.niklas.rechner.util.RedisHelper;

public class Currency {

	private String name;
	private double amount;
	private long time;
	
	public Currency(String name, double amount, long time) {
		this.name = name;
		this.amount = amount;
		this.time = time;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String n) {
		if(!this.name.equals(n)) {
			RedisHelper.set(name, null);
			RedisHelper.srem("currency_set", name);
			
			name = n;
			
			String value = Rechner.gson.toJson(this);
			RedisHelper.set(name, value);
			RedisHelper.sadd("currency_set", name);
		}
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public long getTime() {
		return this.time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public void delete() {
		RedisHelper.set(name, null);
		RedisHelper.srem("currency_set", name);
	}
	
	public void writeIntoDataBase() {
		
		String value = Rechner.gson.toJson(this);
		
		RedisHelper.set(name, value);
		RedisHelper.sadd("currency_set", name);
	}
}
