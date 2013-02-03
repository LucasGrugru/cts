package cts;

import java.util.List;

public class Simulateur {

	List<Processeur> processeurs;
	List<Tache> taches;
	
	public Simulateur() {
		
		
	}
	
	public void simuler() {
		//P la liste des processeurs
		//S = {};U = V ; (*marquer toutes les taches comme non ordonnancees*)
		//Calculer L+(t) pour chaque tache t et mettre L-(t) a 0 pour chaque tache d'entree t;
		//Mettre les taches d'entree dans alpha;
		//while(U!=0) {
			//t  H(alpha) ; (*selectionner une tache libre critique de alpha *)
			//if L-(t) >= E(P*) then
				//Ordonnancer la tache t sur son processeur contraint P*
			//else
				//P = H(phi); (*le processeur qui a la date de disponibilite minimale*)
				//P(t) = P | E(P) = min((Pb), (P*)); (*selection processeur *)
				//Ordonnancer la tache t sur le processeur choisi P;
			//end if
			//Mettre t dans S et mettre a jour les valeurs de priorites des t^aches successeurs de t;
			//Mettre les taches successeurs libres de t dans alpha;
			//U = U\(t);
		//}
	}

}
