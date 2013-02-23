
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import cts.Arete;
import cts.Graphe;
import cts.Tache;

public class TestGraphe {
	
	public Graphe graphe;

	@Before
	public void init() {
		ArrayList<Tache> taches = new ArrayList<Tache>();
		
		taches.add(new Tache(6, 1));
		taches.add(new Tache(4, 2));
		taches.add(new Tache(4, 3));
		taches.add(new Tache(2, 4));
		taches.add(new Tache(5, 5));
		
		graphe = new Graphe(taches);
		
		graphe.ajouteArete(new Arete(2, graphe.getTache(1), graphe.getTache(3)));
		graphe.ajouteArete(new Arete(3, graphe.getTache(1), graphe.getTache(4)));
		
		graphe.ajouteArete(new Arete(1, graphe.getTache(2), graphe.getTache(3)));
		
		graphe.ajouteArete(new Arete(1, graphe.getTache(3), graphe.getTache(5)));
		
		graphe.ajouteArete(new Arete(3, graphe.getTache(4), graphe.getTache(5)));
	}
	
	@Test
	public void testGetBottomLevel() {
		Assert.assertEquals(graphe.getBottomLevel(graphe.getTache(4)), 9);
	}
	
	@Test
	public void testGetTopLevel() {
		Assert.assertEquals(graphe.getTopLevel(graphe.getTache(4)), 10);
	}
	
	@Test
	public void testPriorite() {
		Assert.assertEquals(graphe.getPriorite(graphe.getTache(4)), 19);
	}

}