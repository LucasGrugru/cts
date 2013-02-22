package cts;

import java.util.ArrayList;
import java.util.List;

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

	public List<Tache> getPredecesseurs(Tache tacheSuivante) {
		List<Tache> listPredecesseurs = new ArrayList<Tache>();
		for(Tache t : this.taches) {
			if(this.getSuccesseurs(t).contains(tacheSuivante)) {
				listPredecesseurs.add(t);
			}
		}
		return listPredecesseurs;
	}
	
	public List<Tache> getSuccesseurs(Tache t) {
		List<Tache> taches = new ArrayList<Tache>();
		for(Arete a : aretes) {
			if(a.getCourant().equals(t))
				taches.add(a.getSuccesseur());
		}
		return taches;
	}
	
	public List<Tache> getEntrees() {
		List<Tache> entrees = new ArrayList<Tache>();	
		for(Tache t : this.taches)
			if(t.getTopLevel() == 0)
				entrees.add(t);
		return entrees;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public Tache getFirstFreeCritical() {
		for(Tache t : taches) {
			if(t.etat == Etat.LIBRE)
				return t;
		}
		return null;
	}

	public boolean existeArete(Tache tacheC, Tache tacheS) {
		for(Arete a : aretes) {
			if(a.getCourant().equals(tacheC) && a.getCourant().equals(tacheS))
				return true;
		}
		return false;
	}

	public void ajouteArete(Arete arete) {
		this.aretes.add(arete);
	}

}
