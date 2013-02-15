package cts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;

public class Generateur {

	private int nbNoeuds;
	private Graphe graphe;
	private double granularite;
	
	public Generateur(int nbNoeuds, double granularite) {
		this.nbNoeuds = nbNoeuds;
		this.granularite = granularite;
		this.graphe = new Graphe(genererTache());
		genererGraphe();
	}
	
	private void genererGraphe() {
		
	}
	
	private List<Tache> genererTache() {
		Random r = new Random();
		List<Tache> taches = new ArrayList<Tache>();
		List<Tache> tachesCrees = new ArrayList<Tache>();
		Tache tache;
		int[] t = getIntervalleTemps();
		for(int i=0; i<this.nbNoeuds; i++) {
			tache = new Tache(randInteger(t[0], t[1]), null);
			
			if(!tachesCrees.isEmpty()) {
				tache.addArete(new Arete(i, tachesCrees.get(randInteger(0, tachesCrees.size()))));
			}
			taches.add(tache);
			tachesCrees.add(tache);
			
		}
		
		
		
		return taches;
	}

	private int[] getIntervalleTemps() {
		int[] t = new int[2];
		
		t[0] =  ((int)(100*this.granularite) - Constantes.intervalTemps);
		t[1] =  ((int)(100*this.granularite) + Constantes.intervalTemps);
		
		return t;
	}
	
	private int randInteger(int min, int max)
	{
	    return (int) (min + Math.random() * (max - min + 1));
	}
}
