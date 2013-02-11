package cts;

import java.util.ArrayList;
import java.util.List;

public class Simulateur {

	private List<Processeur> processeurs;
	private Graphe graphe;

	
	public Simulateur(int nbProcesseur, Graphe graphe) {
		initialiser(nbProcesseur);
		this.graphe = graphe;
	}
	
	private void initialiser(int nbProcesseur) {
		for(int i=0; i<nbProcesseur; i++)
			processeurs.add(new Processeur());
	}
	
	
	public void simulerCTS() {
		List<Tache> alpha = this.graphe.getEntrees();
		List<Tache> S = new ArrayList<Tache>(); // liste des taches ordonnancées
		int U = this.graphe.getTaches().size();
		Tache t;
		while(U != 0) {
			t = this.graphe.getFirstFreeCritical();
			if(t == null)
				System.exit(-1);
			if(t.getTopLevel() >= getProcesseurMax(t.getPredecesseurs()).getDisponibilite()) { //date de disponibilité du processeur des predecesseur de t
				
			}
			
		}
		//P la liste des processeurs
		//S = {};U = V ; (*marquer toutes les taches comme non ordonnancees*)
		//Calculer L+(t) pour chaque tache t et mettre L-(t) a 0 pour chaque tache d'entree t;
		//Mettre les taches d'entree dans alpha;
		//while(U!=0) {
			//t = H(alpha) ; (*selectionner une tache libre critique de alpha *)
			//if L-(t) >= E(P*) then
				//Ordonnancer la tache t sur son processeur contraint P*
			//else
				//P = H(phi); (*le processeur qui a la date de disponibilite minimale*)
				//P(t) = P | E(P) = min((Pb), (P*)); (*selection processeur *)
				//Ordonnancer la tache t sur le processeur choisi P;
			//end if
			//Mettre t dans S et mettre a jour les valeurs de priorites des t^aches successeurs de t;
			//Mettre les taches successeurs libres de t dans alpha;
			//U = U\(t);
		//}
	}

	private Processeur getProcesseurMax(List<Tache> predecesseurs) {
		List<Processeur> temp = new ArrayList<Processeur>();
		for(Processeur p : processeurs) {
			for(Tache t : predecesseurs) {
				if(p.inList(t)) {
					
				}
			}
		}
		return null;
	}
}
