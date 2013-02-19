package cts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;

public class Generateur {

	private int nbNoeuds;
	private Graphe graphe;
	private double granularite;
	
	public Generateur(int nbNoeuds, double granularite, int densite) {
		this.nbNoeuds = nbNoeuds;
		this.granularite = granularite;
		this.graphe = new Graphe(genererTache(densite));
	}
	
	private void genererGraphe() {
		
	} 
	
	private List<Tache> genererTache(int densite) {
		List<Tache> taches = new ArrayList<Tache>();
		List<Tache> tachesCrees = new ArrayList<Tache>();
		Tache tacheC;
		Tache tacheS;
		int[] t = getIntervalTempsE();
		for(int i=0; i<this.nbNoeuds; i++) {
			tacheC = new Tache(randInteger(t[0], t[1]), null);
			
			if(!tachesCrees.isEmpty()) {
				this.graphe.ajouteArete(new Arete(i, tacheC, tachesCrees.get(randInteger(0, tachesCrees.size()))));
			}
			taches.add(tacheC);
			tachesCrees.add(tacheC);
		}
		
		for(int i=0; i<densite; i++) {
			int r = randInteger(0,taches.size() - 1);
			tacheC = taches.get(r);
			tacheS = taches.get(randInteger(r + 1, taches.size()));
			if(!this.graphe.existeArete(tacheC, tacheS)) {
				this.graphe.ajouteArete(
						new Arete(
								randInteger(getIntervalTempsC()[0], 
										getIntervalTempsC()[1]), 
								tacheC, 
								tacheS));
			} else {
				i--;
			}
		}
		
		
		return taches;
	}

	private int[] getIntervalTempsE() {
		int[] t = new int[2];
		
		t[0] =  ((int)(100*this.granularite) - Constantes.intervalTemps);
		t[1] =  ((int)(100*this.granularite) + Constantes.intervalTemps);
		
		return t;
	}
	
	private int[] getIntervalTempsC() {
		int[] t = new int[2];
		
		t[0] =  ((int)100 - Constantes.intervalTemps);
		t[1] =  ((int)100 + Constantes.intervalTemps);
		
		return t;
	}
	
	private int randInteger(int min, int max)
	{
	    return (int) (min + Math.random() * (max - min + 1));
	}
}
