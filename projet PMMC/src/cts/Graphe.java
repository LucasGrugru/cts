package cts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graphe {
	
	private List<Tache> taches;
	private List<Arete> aretes;
	
	public Graphe(List<Tache> taches, List<Arete> aretes) {
		this.taches = taches;
		this.aretes = aretes;
	}
	
	public Graphe(List<Tache> taches) {
		this.taches = taches;
		this.aretes = new ArrayList<Arete>();
	}
	
	public Graphe() {
		this.taches = new ArrayList<Tache>();
		this.aretes = new ArrayList<Arete>();
	}

	/**
	 * Calcul els predecesseurs d'une tache
	 * @param tacheSuivante la tache
	 * @return la liste des tache precedant la tache
	 */
	public List<Tache> getPredecesseurs(Tache tacheSuivante) {
		Set<Tache> listPredecesseurs = new HashSet<Tache>();
		for(Tache t : this.taches) {
			if(this.getSuccesseurs(t).contains(tacheSuivante)) {
				listPredecesseurs.add(t);
			}
		}
		return new ArrayList<Tache>(listPredecesseurs);
	}
	
	/**
	 * Calcul les successeurs d'une tache
	 * @param t la tache
	 * @return la liste des successeurs
	 */
	public List<Tache> getSuccesseurs(Tache t) {
		Set<Tache> taches = new HashSet<Tache>();
		for(Arete a : aretes) {
			if(a.getCourant().equals(t))
				taches.add(a.getSuccesseur());
		}
		return new ArrayList<Tache>(taches);
	}
	
	/**
	 * Calcul les taches d'entrée du graphe
	 * @return les taches d'entrée
	 * @throws Exception
	 */
	public List<Tache> getEntrees() throws Exception {
		List<Tache> entrees = new ArrayList<Tache>();	
		for(Tache t : this.taches)
			if(this.getTopLevel(t, false) == 0)
				entrees.add(t);
		return entrees;
	}

	/**
	 * @return la liste des taches
	 */
	public List<Tache> getTaches() {
		return taches;
	}
	
	/**
	 * Recupere une tache 
	 * @param num le numero de la tache recherchee
	 * @return la tache
	 * @throws Exception si la tache n'existe pas
	 */
	public Tache getTache(int num) throws Exception {
		for(Tache t: taches)
			if(t.getNum() == num)
				return t;
		throw new Exception("Tache inexistante");
	}

	public Tache getBestFree() throws Exception {
		for(Tache t : taches) {
			if(t.etat == Etat.LIBRE)
				return t;
		}
		throw new Exception("Aucune tache libre");
	}

	/**
	 * Verifie l'existence d'une arete entre deux taches
	 * @param tacheC la tache courante
	 * @param tacheS la tache suivante
	 * @return 
	 * 		true si l'arete existe
	 * 		false si l'arete n'existe pas
	 */
	public boolean existeArete(Tache tacheC, Tache tacheS) {
		for(Arete a : aretes) {
			if(a.getCourant() == tacheC && a.getSuccesseur() == tacheS)
				return true;
		}
		return false;
	}

	/**
	 * Ajoute une arete au graphe
	 * @param arete l'arete a ajouter
	 */
	public void ajouteArete(Arete arete) {
		this.aretes.add(arete);
	}

	/**
	 * Calcul le toplevel d'une tache
	 * @param tache la tache
	 * @param com prise en compte des temps de communication
	 * @return le toplevel de la tache
	 * @throws Exception
	 */
	public int getTopLevel(Tache tache, boolean com) throws Exception {
		if(this.getPredecesseurs(tache).isEmpty()) {
			return 0;
		} else {
			int max = 0;
			int temp = 0;
			for(Tache t : this.getPredecesseurs(tache)) {
				if(com)
					temp = this.getTopLevel(t, com) + t.getTemps() + this.getCommunication(t, tache);
				else
					temp = this.getTopLevel(t, com) + t.getTemps();
				if(temp > max) {
					max = temp;
				}
			}
			return max;
		}
	}
	
	/**
	 * Calcul le bottomlevel d'une tache
	 * @param tache la tache
	 * @param com prise en compte des temps de communication
	 * @return le bottomlevel
	 * @throws Exception
	 */
	public int getBottomLevel(Tache tache, boolean com) throws Exception {
		if(this.getSuccesseurs(tache).isEmpty()) {
			return tache.getTemps();
		} else {
			int max = 0;
			int temp = 0;
			for(Tache t : this.getSuccesseurs(tache)) {
				if(com)
					temp = tache.getTemps() + this.getBottomLevel(t, com) + this.getCommunication(tache, t);
				else
					temp = tache.getTemps() + this.getBottomLevel(t, com);
				if(temp > max) {
					max = temp;
				}
			}
			return max;
		}	
	}
	
	/**
	 * La priorite d'une tache
	 * @param t la tache
	 * @param com prise en compte des temps de communication
	 * @return la priorite
	 * @throws Exception
	 */
	public int getPriorite(Tache t, boolean com) throws Exception {
		return this.getBottomLevel(t, com) + this.getTopLevel(t, com);
	}
	
	/**
	 *  Renvoie la communication entre deux taches si une arete axiste entre celle ci
	 * @param tacheC la tache courante
	 * @param tacheS la tache suivante
	 * @return le temps de communication de la tache courante vers la tache suivante
	 * @throws Exception si il n'y a pas d'arete entre les deux taches.
	 */
	public int getCommunication(Tache tacheC, Tache tacheS) throws Exception {
		if(this.existeArete(tacheC, tacheS))
			for(Arete a : aretes)
				if(a.getCourant() == tacheC && a.getSuccesseur() == tacheS)
					return a.getTime();
		throw new Exception("Arete inexistante");
	}

	/**
	 * Ajoute une nouvelle tache au graphe
	 * @param t la tache a ajouter
	 */
	public void ajouteTache(Tache t) {
		taches.add(t);
	}
	
	
}
