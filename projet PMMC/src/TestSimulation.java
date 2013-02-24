import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import cts.Arete;
import cts.Graphe;
import cts.Simulateur;
import cts.Tache;


public class TestSimulation {

	private Graphe graphe;
	private Tache tache1;
	private Tache tache2;
	private Tache tache3;
	private Tache tache4;
	private Tache tache5;
	private Simulateur simulateur;

	@Before
	public void init() {
		ArrayList<Tache> taches = new ArrayList<Tache>();
		
		tache1 = new Tache(6, 1);
		tache2 = new Tache(4, 2);
		tache3 = new Tache(4, 3);
		tache4 = new Tache(2, 4);
		tache5 = new Tache(5, 5);
		
		taches.add(tache1);
		taches.add(tache2);
		taches.add(tache3);
		taches.add(tache4);
		taches.add(tache5);
		
		graphe = new Graphe(taches);
		
		graphe.ajouteArete(new Arete(2, tache1, tache3));
		graphe.ajouteArete(new Arete(3, tache1, tache4));
		graphe.ajouteArete(new Arete(1, tache2, tache3));
		graphe.ajouteArete(new Arete(1, tache3, tache5));
		graphe.ajouteArete(new Arete(3, tache4, tache5));
	}
	
	@Test
	public void testCreationProcesseur() {
		
	}

}
