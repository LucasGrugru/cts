package cts;

/**
 * http://github.com/luckyboss1/cts
 * @author lucky
 *
 */
public class Tache {

	/**
	 * Temps d'execution de la tache
	 */
	private int temps;
	
	/**
	 * Numero de la tache
	 */
	private int num;
	
	/**
	 * Etat d'ordonnancement de la tache
	 */
	public Etat etat;
	
	/**
	 * instant du ebut de la tache sur un processeur
	 */
	public int debut;
	
	/**
	 * processeur sur lequel est ordonnancé la tache
	 */
	public Processeur processeur;
	
	/**
	 * La valeur du bottom level calculé une fois puis stocké ici
	 */
	public int bottomLevelCom;
	
	/**
	 * La valeur du top level calculé une fois puis stocké ici
	 */
	public int topLevelCom;
	
	/**
	 * La valeur du bottom level calculé sans prise en compte des temps de communication
	 */
	public int bottomLevel;
	
	/**
	 * La valeur du top level calculé sans prise en compte des temps de communication
	 */
	public int topLevel;
	
	public Tache(int temps, int num) { 
		this.num = num;
		this.temps = temps;
		this.etat = Etat.NON_LIBRE;
		this.debut = -1;
		this.processeur = null;
		this.bottomLevel = -1;
		this.topLevel = -1;
		this.bottomLevelCom = -1;
		this.topLevelCom = -1;
	}
	
	/**
	 * @return le temps d'execution de la tache
	 */
	public int getTemps() {
		return this.temps;
	}

	/**
	 * Met à i le lancement de la tache
	 * @param i l'instant de debut de la tache
	 */
	public void begin(int i) {
		this.debut = i;
	}

	/**
	 * @return le numero de la tache dans le graphe
	 */
	public int getNum() {
		return this.num;
	}
	
}


