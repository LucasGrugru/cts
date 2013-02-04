package cts;

import java.util.List;

public class Tache {

	private int temps;
	private List<Tache> predecesseurs;
	public Etat etat;
	
	public Tache(int temps, List<Tache> predecesseurs) {
		this.temps = temps;
		this.predecesseurs = predecesseurs;
		etat = Etat.LIBRE;
	}
	
	public List<Tache> getPredecesseurs() {
		return this.predecesseurs;
	}
	
	public int getTopLevel() {
		if(predecesseurs.isEmpty()) {
			return 0;
		} else {
			int max = 0;
			int temp = 0;
			for(Tache t : predecesseurs) {
				temp = t.getTopLevel() + t.getTemps() + this.comm(t);
				if(temp > max) {
					max = temp;
				}
			}
			return max;
		}
			
	}
	
	private int getTemps() {
		return this.temps;
	}

	private int comm(Tache t) {
		return 0;
	}
	
	public void run() throws InterruptedException {
		Thread.sleep(temps);
	}
	
}
