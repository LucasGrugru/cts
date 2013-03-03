package cts;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class Generateur {

	private int nbNoeuds;
	public Graphe graphe;
	private double granularite;
	
	public Generateur(int nbNoeuds, double granularite, int densite) {
		this.nbNoeuds = nbNoeuds;
		this.granularite = granularite;
		this.graphe = new Graphe();
		genererTache(densite);
	}
	
	public Generateur(int nbNoeuds, double granularite) {
		this.nbNoeuds = nbNoeuds;
		this.granularite = granularite;
		this.graphe = new Graphe();
		genererTache(randInteger(0, nbNoeuds));
	}
	
	private void genererTache(int densite) {
		List<Tache> tachesCrees = new ArrayList<Tache>();
		Tache tacheC;
		Tache tacheS;
		Arete a;
		int[] te = getIntervalTempsE();
		int[] tc = getIntervalTempsC();
		for(int i=0; i<this.nbNoeuds; i++) {
			tacheC = new Tache(randInteger(te[0], te[1]), this.nbNoeuds - i);
			
			if(!tachesCrees.isEmpty()) {
				tacheS = tachesCrees.get(randInteger(0, tachesCrees.size()-1));
				a = new Arete(randInteger(tc[0], tc[1]), tacheC, tacheS);
				if(!this.graphe.existeArete(a.getCourant(), a.getSuccesseur()) && a.getCourant().getNum() > a.getSuccesseur().getNum())
					this.graphe.ajouteArete(new Arete(randInteger(tc[0], tc[1]), tacheC, tacheS));
				System.out.println(i+" : TacheC : "+tacheC.getNum()+", TacheS : "+tacheS.getNum());
			}
			tachesCrees.add(tacheC);
			this.graphe.ajouteTache(tacheC);
		}
		
		/*
		for(int i=0; i<densite; i++) {
			int r = randInteger(0,taches.size() - 1);
			if(r < taches.size() - 1) {
				tacheC = taches.get(r);
				tacheS = taches.get(randInteger(r + 1, taches.size()-1));
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
		}*/
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
