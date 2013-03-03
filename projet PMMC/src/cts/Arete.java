package cts;

/**
 * http://github.com/luckyboss1/cts
 * @author lucky
 *
 */
public class Arete {

	/**
	 * Temps de communication de cette arete
	 */
	private int time;
	
	/**
	 * Tache vers laquelle pointe l'arete
	 */
	private Tache tacheS;
	
	/**
	 * Tache de départ de l'arete
	 */
	private Tache tacheC;
	
	public Arete(int time, Tache tacheC, Tache tacheS) {
		this.time = time;
		this.tacheS = tacheS;
		this.tacheC = tacheC;
	}
	
	/**
	 * @return le temps de communication de l'arete
	 */
	public int getTime() {
		return this.time;
	}
	
	/**
	 * @return la tache vers laquelle pointe l'arete
	 */
	public Tache getSuccesseur() {
		return this.tacheS;
	}

	/**
	 * @return la tache de depart de l'aret
	 */
	public Tache getCourant() {
		return this.tacheC;
	}
}
