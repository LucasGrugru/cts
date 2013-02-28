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
	
	/**
	 * Initialise le bon nombre de processeur pour simuler l'ordonnancement du graphe
	 * @param nbProcesseur le nombre de processeur souhaité pour la simulation.
	 */
	private void initialiser(int nbProcesseur) {
		processeurs = new ArrayList<Processeur>();
		for(int i=0; i<nbProcesseur; i++)
			processeurs.add(new Processeur());
	}
	
	/**
	 * Effectue une simulation de l'algorithme d'ordonnancement CTS
	 * @return	la makespan de la simulation
	 * @throws Exception exception retourné en cas de mauvaise construction du graphe.
	 */
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

	/**
	 * Met les taches de alpha disponibles pour etre ordonnancees.
	 * @param alpha
	 */
	private void setLibre(List<Tache> alpha) {
		for(Tache t : alpha) {
			t.etat = Etat.LIBRE;
		}
		
	}

	/**
	 * 
	 * @return	le processeur avec la disponibilité minimale
	 */
	private Processeur getMinDispo() {
		return Collections.min(this.processeurs);
	}
	
	/**
	 * 
	 * @return	le processeur avec la disponibilité maximale
	 */
	private Processeur getMaxDispo() {
		return Collections.max(this.processeurs);
	}

	/**
	 * 
	 * @param alpha une liste de tache
	 * @return	la tache ayant la meilleur priorite		
	 * @throws Exception renvoyé par getPriorité en cas de tache inexistante (generalement mauvaise construction du graphe)
	 */
	private Tache getBestFree(List<Tache> alpha) throws Exception{
		Tache t2 = null;
		int temp = 0;
		for(Tache t1 : alpha){
			if(this.graphe.getPriorite(t1) > temp ){
				temp = this.graphe.getPriorite(t1);
				t2 = t1;
			}
		}
		return t2;
	}

	/**
	 * Recupere le processeur ayant la disponibilité la plus haute d'une liste de tache.
	 * @predecesseur  la liste de tache.
	 * @return le processeur ayant la disponibilite la plus haute.
	 */
	private Processeur getProcesseurMaxDispo(List<Tache> predecesseurs) {
		int temps = getMaxDispo().getDisponibilite();
		Processeur pRecherche = getMaxDispo();
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
