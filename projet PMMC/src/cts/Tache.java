package cts;

import java.util.ArrayList;
import java.util.List;

public class Tache {

	private int temps;
	private List<Arete> successeurs;
	private Graphe graphe;
	public Etat etat;
	
	public Tache(int temps, List<Arete> successeurs) {
		this.temps = temps;
		this.successeurs = successeurs;
		etat = Etat.LIBRE;
	}
	
	public List<Tache> getSuccesseurs() {
		List<Tache> taches = new ArrayList<Tache>();
		for(Arete a : successeurs) {
			taches.add(a.getSuccesseur());
		}
		return taches;
	}
	
	public int getTopLevel() {
		if(this.graphe.getPredecesseurs(this).isEmpty()) {
			return 0;
		} else {
			int max = 0;
			int temp = 0;
			for(Tache t : this.graphe.getPredecesseurs(this)) {
				temp = t.getTopLevel() + t.getTemps() + this.getCommunication(t);
				if(temp > max) {
					max = temp;
				}
			}
			return max;
		}
	}
	
	public int getBottomLevel() {
		if(this.getSuccesseurs().isEmpty()) {
			return 0;
		} else {
			int max = 0;
			int temp = 0;
			for(Tache t : this.getSuccesseurs()) {
				temp = t.getBottomLevel() + t.getTemps() + this.getCommunication(t);
				if(temp > max) {
					max = temp;
				}
			}
			return max;
		}	
	}
	
	public int getPriorite() {
		return getBottomLevel() + getTopLevel();
	}
	
	private int getTemps() {
		return this.temps;
	}

	private int getCommunication(Tache t) {
		return this.successeurs.get( 
					this.successeurs.indexOf(t) 
				).getTime();
	}
	
	public void run() throws InterruptedException {
		Thread.sleep(temps);
	}
	
}
