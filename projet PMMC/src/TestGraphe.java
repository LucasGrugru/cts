
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import cts.Arete;
import cts.Graphe;
import cts.Tache;

public class TestGraphe {
	
	private Graphe graphe;
	private Tache tache1;
	private Tache tache2;
	private Tache tache3;
	private Tache tache4;
	private Tache tache5;

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
	public void testGetSuccesseurs() {
		Assert.assertTrue(graphe.getSuccesseurs(tache4).contains(tache5));
		
		Assert.assertFalse(graphe.getSuccesseurs(tache4).contains(tache4));
		Assert.assertFalse(graphe.getSuccesseurs(tache4).contains(tache3));
		Assert.assertFalse(graphe.getSuccesseurs(tache4).contains(tache2));
		Assert.assertFalse(graphe.getSuccesseurs(tache4).contains(tache1));
	}
	
	@Test
	public void testGetPredecesseurs() {
		Assert.assertTrue(graphe.getPredecesseurs(tache4).contains(tache1));
		
		Assert.assertFalse(graphe.getPredecesseurs(tache4).contains(tache4));
		Assert.assertFalse(graphe.getPredecesseurs(tache4).contains(tache3));
		Assert.assertFalse(graphe.getPredecesseurs(tache4).contains(tache2));
		Assert.assertFalse(graphe.getPredecesseurs(tache4).contains(tache5));
	}
	
	@Test
	public void testGetCommunication() {
		
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