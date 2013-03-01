import cts.Constantes;
import cts.Generateur;
import cts.Graphe;
import cts.Simulateur;


public class Main {

	public static void main(String [] args) throws Exception{ 	
		Graphe graphe = null;
		Generateur g = null;
		Simulateur s = new Simulateur(Constantes.NOMBRE_PROC);

		int nbr_taches = 500;
		int nbr_graphe_par_granularite = 20;
		double granTab[] = {0.2, 0.4, 0.6, 0.8, 1, 1.2, 1.4, 1.6, 1.8, 2};

		
		int[] makespanCTS = new int[granTab.length+1];
		int[] makespanMAX = new int[granTab.length+1];

		//POUR TEST 
		/*g = new Graph();
		System.out.println(g);
		int make = g.cts(proc, false);
		System.out.println("Makespan : "+make);*/

		int tempCTS;
		int tempMAX;
		makespanCTS[0] = 0;
		makespanMAX[0] = 0;
		for(int i = 0 ; i < granTab.length; i++){

			makespanCTS[i+1] = 0;
			makespanMAX[i+1] = 0;
			tempCTS = 0;
			tempMAX = 0;
			for(int j =0 ; j < nbr_graphe_par_granularite; j++){

				g = new Generateur(nbr_taches, granTab[i]); 
				graphe = g.graphe;
				s.setGraphe(graphe);
				
				//MAKESPAN CTS
				tempCTS += s.simulerCTS();

				//MAKESPAN MAX
				tempMAX += s.simulerBestCTS();

			}
			//System.out.println(temp);
			makespanCTS[i+1] = tempCTS/nbr_graphe_par_granularite;
			makespanMAX[i+1] = tempMAX/nbr_graphe_par_granularite;
		}

		Courbe courbe = new Courbe("PMMC - Ordonancement","Stats CTS/OPT",makespanCTS,makespanMAX);
		courbe.pack();
		courbe.setVisible(true);
	}
}
