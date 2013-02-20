package cts;

import java.util.ArrayList;
import java.util.List;

public class Tache {

	private int temps;
	private int num;
	private Graphe graphe;
	public Etat etat;
	public int debut;
	
	public Tache(int temps, int num) {
		this.num = num;
		this.temps = temps;
		etat = Etat.LIBRE;
		this.debut = -1;
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
		if(this.graphe.getSuccesseurs(this).isEmpty()) {
			return 0;
		} else {
			int max = 0;
			int temp = 0;
			for(Tache t : this.graphe.getSuccesseurs(this)) {
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
	
	public int getTemps() {
		return this.temps;
	}

	private int getCommunication(Tache t) {
		return this.graphe.getSuccesseurs(this).get( 
					this.graphe.getSuccesseurs(this).indexOf(t) 
				).getTemps();
	}

	public void begin(int i) {
		this.debut = i;
	}
	
}
