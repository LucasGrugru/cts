package cts;

public class Arete {

	private int time;
	private Tache tacheS;
	private Tache tacheC;
	
	public Arete(int time, Tache tacheC, Tache tacheS) {
		this.time = time;
		this.tacheS = tacheS;
		this.tacheC = tacheC;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public Tache getSuccesseur() {
		return this.tacheS;
	}

	public Tache getCourant() {
		return this.tacheC;
	}
}
