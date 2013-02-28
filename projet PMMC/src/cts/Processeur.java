package cts;

import java.util.ArrayList;
import java.util.List;

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

	public void ordonnancer(Tache t, int debut) {
		this.file.add(t);
		t.begin(this.disponibilite + debut);
		this.disponibilite += t.getTemps();
		t.etat = Etat.ORDONNANCE;
	}
	

	
}
