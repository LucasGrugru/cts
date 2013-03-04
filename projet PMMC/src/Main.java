import cts.Constantes;
import cts.Generateur;
import cts.Graphe;
import cts.Simulateur;


public class Main {

	public static void main(String [] args) throws Exception{ 	
		Graphe graphe = null;
		Generateur g = null;
		Simulateur s = new Simulateur(Constantes.NOMBRE_PROC);

		int nbtaches = 10;
		int nbgraphegran = 500;
		double granTab[] = {0.2, 0.4, 0.6, 0.8, 1, 1.2, 1.4, 1.6, 1.8, 2};

		int[] makespanCTS = new int[granTab.length+1];
		int[] makespanMAX = new int[granTab.length+1];

		int tempCTS;
		int tempMAX;
		makespanCTS[0] = 0;
		makespanMAX[0] = 0;
		for(int i = 0 ; i < granTab.length; i++){

			makespanCTS[i+1] = 0;
			makespanMAX[i+1] = 0;
			tempCTS = 0;
			tempMAX = 0;
			for(int j =0 ; j < nbgraphegran; j++){

				g = new Generateur(nbtaches, granTab[i]); 
				graphe = g.graphe;
				s.setGraphe(graphe);

				tempCTS += s.simulerCTS();

				tempMAX += s.simulerBestCTS();

			}
			makespanCTS[i+1] = tempCTS/nbgraphegran;
			makespanMAX[i+1] = tempMAX/nbgraphegran;
		}

		Courbe courbe = new Courbe("Statistique d'ordonnancement selon l'algorithme CTS","CTS/MAX",makespanCTS,makespanMAX);
		courbe.pack();
		courbe.setVisible(true);
	}
}
