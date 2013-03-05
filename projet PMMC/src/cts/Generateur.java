package cts;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

/**
 * http://github.com/luckyboss1/cts
 * @author lucky
 *
 */
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
	
	/**
	 * Genere les taches du graphe ainsi que les aretes associés
	 * @param densite nombre d'arete à ajouter apres la generation de chaque tache et arete associé
	 */
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
				if(!this.graphe.existeArete(a.getCourant(), a.getSuccesseur()))
					this.graphe.ajouteArete(new Arete(randInteger(tc[0], tc[1]), tacheC, tacheS));
			}
			tachesCrees.add(tacheC);
			this.graphe.ajouteTache(tacheC);
		}
		
		for(int i=0; i<densite; i++) {
			int r = randInteger(0,this.graphe.getTaches().size() - 1);
			if(r < this.graphe.getTaches().size() - 1) {
				tacheC = this.graphe.getTaches().get(r);
				tacheS = this.graphe.getTaches().get(randInteger(r + 1, this.graphe.getTaches().size()-1));
				if(!this.graphe.existeArete(tacheC, tacheS) && tacheS.getNum() > tacheC.getNum()) {
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
		}
	}

	/**
	 * Calcul l'interval de temps d'execution en fonction de la granularite souhaitee
	 * @return tableau d'entier contenant la borne superieur et inferieur de l'interval
	 */
	private int[] getIntervalTempsE() {
		int[] t = new int[2];
		
		t[0] =  ((int)(Constantes.ordre_grandeur*this.granularite) - Constantes.intervalTemps);
		t[1] =  ((int)(Constantes.ordre_grandeur*this.granularite) + Constantes.intervalTemps);
		
		return t;
	}

	/**
	 * Calcul l'interval de temps de communication
	 * @return tableau d'entier contenant la borne superieur et inferieur de l'interval
	 */
	private int[] getIntervalTempsC() {
		int[] t = new int[2];
		
		t[0] =  ((int)Constantes.ordre_grandeur - Constantes.intervalTemps);
		t[1] =  ((int)Constantes.ordre_grandeur + Constantes.intervalTemps);
		
		return t;
	}
	
	/**
	 * Calcul un entier aléatoire
	 * @param min borne supérieur pour l'entier
	 * @param max borne inferieur pour l'entier
	 * @return	un entier aléatoire entre min et max
	 */
	private int randInteger(int min, int max)
	{
	    return (int) (min + Math.random() * (max - min + 1));
	}
}
