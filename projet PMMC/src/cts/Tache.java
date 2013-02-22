package cts;


public class Tache {

	private int temps;
	private int num;
	public Etat etat;
	public int debut;
	
	public Tache(int temps, int num) {
		this.num = num;
		this.temps = temps;
		etat = Etat.LIBRE;
		this.debut = -1;
	}
	
	public int getTemps() {
		return this.temps;
	}

	public void begin(int i) {
		this.debut = i;
	}

	public int getNum() {
		return this.num;
	}
	
}


