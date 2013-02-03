package cts;

import java.util.List;

public class Tache {

	private int sleep;
	private List<Tache> precedentes;
	
	public Tache(int sleep, List<Tache> precedentes) {
		this.sleep = sleep;
		this.precedentes = precedentes;
	}
	
	public void run() throws InterruptedException {
		Thread.sleep(sleep);
	}
}
