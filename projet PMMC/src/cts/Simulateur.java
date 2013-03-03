package cts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * http://github.com/luckyboss1/cts
 * @author lucky
 *
 */
public class Simulateur {

	/**
	 * Liste des processeurs pour l'ordonnancement
	 */
	private List<Processeur> processeurs;
	/**
	 * Le graphe de taches à ordonnancer
	 */
	private Graphe graphe;

	
	public Simulateur(int nbProcesseur, Graphe graphe) {
		initialiser(nbProcesseur);
		this.graphe = graphe;
	}
	
	public Simulateur(int nbProcesseur) {
		initialiser(nbProcesseur);
		this.graphe = null;
	}
	
	/**
	 * Modifier le graphe a simuler
	 * @param g le nouveau graphe
	 */
	public void setGraphe(Graphe g) {
		this.graphe = g;
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
		int top;
		int l;
		while(!alpha.isEmpty()) {
			t = this.getBestFree(alpha, true);
			
			p = getProcesseurMinDispo(graphe.getPredecesseurs(t));
			top = this.graphe.getTopLevel(t, true);
			if(top >= p.getDisponibilite()) { //date de disponibilite du processeur des predecesseurs de t
				l = getLambda(p, t, true);
				p.ordonnancer(t, l);
			} else {
				if(p.getDisponibilite() > getMinDispo().getDisponibilite())
					p = getMinDispo();
				l = getLambda(p, t, true);
				p.ordonnancer(t, l);	
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
		
	}

	/**
	 * Calcule la valeur de lambda
	 * @param p processeur sur lequel va etre ordonnancé t
	 * @param t la tache qui va etre ordonnancé sur p
	 * @param com prise en compte des temps de communication
	 * @return la valeur de lambda.
	 * @throws Exception
	 */
	private int getLambda(Processeur p, Tache t, boolean com) throws Exception{
		int tmp = p.getDisponibilite();
		int aux;
		for(Tache t2 : this.graphe.getPredecesseurs(t)){
			if(t2.processeur != p){
				aux = this.graphe.getTopLevel(t2, com)+t2.getTemps();
				if(aux > tmp){
					tmp = aux;
				}
				if(com){
					aux += this.graphe.getCommunication(t2, t);
				}
			}
		}
		return tmp;
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
	 * Calcule la meilleur tache à ordonnancer
	 * @param alpha une liste de tache
	 * @param com prise en compte des temps de communication
	 * @return	la tache ayant la meilleur priorite		
	 * @throws Exception renvoyé par getPriorité en cas de tache inexistante (generalement mauvaise construction du graphe)
	 */
	private Tache getBestFree(List<Tache> alpha, boolean com) throws Exception{
		Tache t2 = null;
		int temp = 0;
		for(Tache t1 : alpha){
			if(this.graphe.getPriorite(t1, com) >= temp ){
				temp = this.graphe.getPriorite(t1, com);
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
	private Processeur getProcesseurMinDispo(List<Tache> predecesseurs) {
		int temps = getMaxDispo().getDisponibilite();
		Processeur pRecherche = getMaxDispo();
		for(Processeur p : processeurs) {
			for(Tache t : predecesseurs) {
				if(p.inList(t)) {
					if(t.debut + t.getTemps() <= temps) {
						pRecherche = p;
						temps = t.debut + t.getTemps();
					}
				}
			}
		}
		return pRecherche;
	}

	/**
	 * Calcule du makespan ideal (avec le maximum de proc et pas de temps de com)
	 * @return le makespan maximum obtenable
	 * @throws Exception
	 */
	public int simulerBestCTS() throws Exception {
		initialiser(this.graphe.getTaches().size());
		List<Tache> alpha = this.graphe.getEntrees();
		setLibre(alpha);
		List<Tache> S = new ArrayList<Tache>(); // liste des taches ordonnancees
		List<Tache> preds; //liste temporaire des predecesseurs
		Tache t;
		Processeur p;
		int top;
		while(!alpha.isEmpty()) {
			t = this.getBestFree(alpha, false);
			
			p = getProcesseurMinDispo(graphe.getPredecesseurs(t));
			top = this.graphe.getTopLevel(t, false);
			if(top >= p.getDisponibilite()) { //date de disponibilite du processeur des predecesseurs de t
				p.ordonnancer(t, getLambda(p, t, false));
			} else {
				if(p.getDisponibilite() > getMinDispo().getDisponibilite())
					p = getMinDispo();
				
				p.ordonnancer(t, getLambda(p, t, false));
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
	}
}
