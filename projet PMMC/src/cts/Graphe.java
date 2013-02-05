package cts;

import java.util.ArrayList;
import java.util.List;

public class Graphe {
	
	List<Tache> taches;
	
	public Graphe(List<Tache> taches) {
		this.taches = taches;
	}

	public List<Tache> getPredecesseurs(Tache tacheSuivante) {
		List<Tache> listPredecesseurs = new ArrayList<Tache>();
		for(Tache t : this.taches) {
			if(t.getsuccesseurs().contains(tacheSuivante)) {
				listPredecesseurs.add(t);
			}
		}
		return listPredecesseurs;
	}
	
	public List<Tache> getEntrees() {
		List<Tache> entrees = new ArrayList<Tache>();	
		for(Tache t : this.taches)
			if(t.getTopLevel() == 0)
				entrees.add(t);
		return entrees;
	}
}
