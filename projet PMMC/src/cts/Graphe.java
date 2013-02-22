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
			if(this.getTopLevel(t) == 0)
				entrees.add(t);
		return entrees;
	}

	public List<Tache> getTaches() {
		return taches;
	}
	
	public Tache getTache(int num) {
		for(Tache t: taches)
			if(t.getNum() == num)
				return t;
		return null;
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

	public int getTopLevel(Tache tache) {
		if(this.getPredecesseurs(tache).isEmpty()) {
			return 0;
		} else {
			int max = 0;
			int temp = 0;
			for(Tache t : this.getPredecesseurs(tache)) {
				temp = this.getTopLevel(t) + t.getTemps() + this.getCommunication(tache, t);
				if(temp > max) {
					max = temp;
				}
			}
			return max;
		}
	}
	
	public int getBottomLevel(Tache tache) {
		if(this.getSuccesseurs(tache).isEmpty()) {
			return tache.getTemps();
		} else {
			int max = 0;
			int temp = 0;
			for(Tache t : this.getSuccesseurs(tache)) {
				temp = this.getBottomLevel(t) + t.getTemps() + this.getCommunication(tache, t);
				if(temp > max) {
					max = temp;
				}
			}
			return max;
		}	
	}
	
	public int getPriorite(Tache t) {
		return this.getBottomLevel(t) + this.getTopLevel(t);
	}
	
	public int getCommunication(Tache tacheC, Tache tacheS) {
		for(Arete a : aretes)
			if(a.getCourant().equals(tacheC) && a.getSuccesseur().equals(tacheS))
				return a.getTime();
		return (Integer) null;
	}
}
