package me.niklas.rechner.listener;

import me.niklas.rechner.Rechner;

public class ReconnectListener {

	@SuppressWarnings("deprecation")
	public static void reconnect() {
		
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				
				Rechner.connect();
				
				Thread.currentThread().stop();
			}
			
		});
		
		t.start();
	}
}
