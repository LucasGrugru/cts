package cts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulateur {

	private List<Processeur> processeurs; //2 processeurs
	private Graphe graphe;

	
	public Simulateur(int nbProcesseur, Graphe graphe) {
		initialiser(nbProcesseur);
		this.graphe = graphe;
	}
	
	private void initialiser(int nbProcesseur) {
		for(int i=0; i<nbProcesseur; i++)
			processeurs.add(new Processeur());
	}
	
	
	public int simulerCTS() throws Exception {
		List<Tache> alpha = this.graphe.getEntrees();
		setLibre(alpha);
		List<Tache> S = new ArrayList<Tache>(); // liste des taches ordonnancees
		List<Tache> preds; //liste temporaire des predecesseurs
		Tache t;
		Processeur p;
		while(!alpha.isEmpty()) {
			t = this.getBestFree(alpha);
			
			p = getProcesseurMaxDispo(graphe.getPredecesseurs(t));
			
			if(this.graphe.getTopLevel(t) >= p.getDisponibilite()) { //date de disponibilite du processeur des predecesseurs de t
				p.ordonnancer(t, 0);
			} else {
				if(p.getDisponibilite() > getMinDispo().getDisponibilite())
					p = getMinDispo();
				p.ordonnancer(t, p.getDisponibilite());
				
			}
			S.add(t);
			for(Tache t1 : this.graphe.getSuccesseurs(t)) {
				preds = this.graphe.getPredecesseurs(t1);
				if(S.containsAll(preds)) {
					alpha.add(t1);
				}
			}
			alpha.remove(t);
		}
		
		return getMaxDispo().getDisponibilite();
		
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

	private void setLibre(List<Tache> alpha) {
		for(Tache t : alpha) {
			t.etat = Etat.LIBRE;
		}
		
	}

	private Processeur getMinDispo() {
		return Collections.min(this.processeurs);
	}
	
	private Processeur getMaxDispo() {
		return Collections.max(this.processeurs);
	}
	
	private int getMakespan() {
		return Collections.max(this.processeurs).getDisponibilite();
	}

	public Tache getBestFree(List<Tache> alpha) throws Exception{
		Tache t1, t2 = null;
		int temp;
		temp = 0;
		for(int i = 0; i < alpha.size(); i++){
			t1 = alpha.get(i);
			if(this.graphe.getPriorite(t1) > temp ){
				temp = this.graphe.getPriorite(t1);
				t2 = t1;
			}
		}
		return t2;
	}

	/*
	 * Recupere le processeur ayant la disponibilité la plus haute d'une liste de tache.
	 * @predecesseur  la liste de tache.
	 */
	private Processeur getProcesseurMaxDispo(List<Tache> predecesseurs) {
		int temps = getMaxDispo().getDisponibilite();
		Processeur pRecherche = null;
		for(Processeur p : processeurs) {
			for(Tache t : predecesseurs) {
				if(p.inList(t)) {
					if(t.debut + t.getTemps() >= temps) {
						pRecherche = p;
						temps = t.debut + t.getTemps();
					}
				}
			}
		}
		return pRecherche;
	}
}
