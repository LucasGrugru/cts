package cts;

import java.util.List;

public class Tache {

	private int temps;
	private List<Tache> successeurs;
	private Graphe graphe;
	public Etat etat;
	
	public Tache(int temps, List<Tache> successeurs) {
		this.temps = temps;
		this.successeurs = successeurs;
		etat = Etat.LIBRE;
	}
	
	public List<Tache> getsuccesseurs() {
		return this.successeurs;
	}
	
	public int getTopLevel() {
		if(this.graphe.getPredecesseurs(this).isEmpty()) {
			return 0;
		} else {
			int max = 0;
			int temp = 0;
			for(Tache t : this.graphe.getPredecesseurs(this)) {
				temp = t.getTopLevel() + t.getTemps() + this.comm(t);
				if(temp > max) {
					max = temp;
				}
			}
			return max;
		}
	}
	
	public int getBottomLevel() {
		if(this.successeurs.isEmpty()) {
			return 0;
		} else {
			int max = 0;
			int temp = 0;
			for(Tache t : this.successeurs) {
				temp = t.getBottomLevel() + t.getTemps() + this.comm(t);
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

	private int comm(Tache t) {
		return 1;
	}
	
	public void run() throws InterruptedException {
		Thread.sleep(temps);
	}
	
}
