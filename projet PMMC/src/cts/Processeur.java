package cts;

import java.util.ArrayList;
import java.util.List;

/**
 * http://github.com/luckyboss1/cts
 * @author lucky
 *
 */
public class Processeur implements Comparable<Object> {

	private int disponibilite;
	private List<Tache> file; 
	
	public Processeur() {
		this.disponibilite = 0;
		this.file = new ArrayList<Tache>();
	}
	
	public int getDisponibilite() {
		return this.disponibilite;
	}

	/**
	 * Verifie si la tache t est ordonnancé sur ce processeur
	 * @param t la tache à verifier
	 * @return 	true si la tache est ordonnancé sur le processeur
	 * 			false si la tache n'est pas ordonnancé sur le processeur
	 */
	public boolean inList(Tache t) {
		if(this.file.contains(t))
			return true;
		else
			return false;
	}

	@Override
	public int compareTo(Object arg0) {
		Processeur p = (Processeur)arg0;
		if(this.getDisponibilite() > p.getDisponibilite())
			return 1;
		else if(this.getDisponibilite() == p.getDisponibilite())
			return 0;
		else
			return -1;
	}

	/**
	 * Ordonnance une tache sur le processeur
	 * @param t la tache à ordnnancer
	 * @param time le temps du debut de la tache sur ce processeur
	 */
	public void ordonnancer(Tache t, int time) {
		if(t == null)
			System.out.println("tache à ordonnancer null");
		this.file.add(t);
		t.begin(this.disponibilite + (time - t.getTemps()));
		this.disponibilite = time + t.getTemps();
		t.etat = Etat.ORDONNANCE;
		t.processeur = this;
	}
	

	
}
