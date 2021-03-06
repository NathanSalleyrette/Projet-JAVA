package tests;

import java.awt.Color;
import java.util.*;
import pluscourtchemin.*;
import briques.*;
import gui.GUISimulator;
import robots.*;
import simulation.Simulateur;
import java.util.LinkedList;
import evenements.*;

public class TestChemin {

	public static void main(String[] args) {
		// Test pour la carte et les cases
		//Case c = new Case(9,5);
		Case c;
		//System.out.println("case_1" + c);
		//Carte ct1 = new Carte(2, 4); //matrice 2x4
		//System.out.println("carte_1" + ct1);
		NatureTerrain m[][] = {
				{NatureTerrain.EAU, NatureTerrain.FORET, NatureTerrain.EAU},
				{NatureTerrain.HABITAT, NatureTerrain.HABITAT, NatureTerrain.TERRAIN_LIBRE}
		};
		Carte ct2 = new Carte(m);
		//System.out.println("carte_2" + ct2);
		//System.out.println(ct2.getVoisin(ct2.getCase(0, 0), Direction.OUEST));
		
		c = ct2.getCase(0, 0);
		//System.out.println("case_1 : " + c);
		//c.setTerrain(NatureTerrain.FORET);
		//System.out.println("case_1 : " + c);
		//c = ct2.getCase(0, 0);
		//System.out.println("case_1 : " + c);
		System.out.println("voisins case 0, 0 : " + ct2.getVoisins(ct2.getCase(0, 0)));
		
		LinkedList<Robot> robots = new LinkedList<Robot>();
		LinkedList<Incendie> incendies = new LinkedList<Incendie>();
		Drone drone = new Drone(c, ct2);
		//c.setTerrain(NatureTerrain.EAU);
		c = ct2.getCase(1, 0);
		Roues roues = new Roues(c, ct2);
		robots.add(drone);
		robots.add(roues);
		//drone.pushQueue(roues);
		//c.setTerrain(NatureTerrain.FORET);
		roues.remplirReserve();
		drone.remplirReserve();
		//System.out.println(drone.allToString());
		roues.deverserEau(500);
		drone.deverserEau(-10);
		//System.out.println(drone.allToString());
		
		
		c = ct2.getCase(0, 2);
		Incendie incendie1 = new Incendie(c, 50, incendies);
		c = ct2.getCase(0, 0);
		Incendie incendie2 = new Incendie(c, 10, incendies);
		//incendie1.pushQueue(incendie2);
		//System.out.println(incendie1.allToString());
		incendie1.eteindre(60);
		incendie2.eteindre(8);
		//System.out.println(incendie1.allToString());
		
		c = ct2.getCase(0, 2);
		//System.out.println("case_1 : " + c);
		//System.out.println("carte_2 " + ct2);
		System.out.println("carte_2 " + drone.getCarte());
		
		System.out.println();
		System.out.println();
		DonneesSimulation donnees = new DonneesSimulation(ct2, incendies, robots);
		/*
		donnees.deplacer(Direction.EST, drone);
		donnees.bougerRobot(Direction.EST, drone);
		donnees.bougerRobot(Direction.SUD, drone);
		*/
		//donnees.eteindreIncendie(drone, incendie2, 2);
		System.out.println(donnees);
		
		GUISimulator gui = new GUISimulator(800, 600, Color.BLACK);
		Simulateur simulateur = new Simulateur(gui, Color.decode("#ffffff"), donnees);
		
		/* ATTENTION : on ne peut pas definir plusieur bouger case pour un meme robot d'affili=�e : le robot n'a pas encore bouger au momem
		 * ou le deuxi�me mouvement et defenit, ses voisins disponible ont changer
		 * DONC le test suivant est faux, pour le faire marcher j'ai bidouill� les dates
		 */
		Case destination = ct2.getCase(1, 2);
		System.out.println("case : " + destination);
		List<Case> chemin = roues.getChemin(destination);
		System.out.println("chemin : " + chemin);
		for (Case e : chemin) {
        	System.out.println(e.toString());
        }
		System.out.println("fin");
        // Print the shortest path to all the Noeuds 
        // from the source Noeud 
		/*
        System.out.println("The shorted path from Noeud :"); 
        for (int i = 0; i < roues.getDijkstra().getDist().length; i++) {
        	if (roues.getDijkstra().getDist()[i] == Integer.MAX_VALUE)
        		System.out.println("Inatteignable");
        	else
            System.out.println(depart + " to " + i + " is "
                               + roues.getDijkstra().getDist()[i]); 
			
            roues.getDijkstra().printPath(i, roues.getDijkstra().getparents());
            System.out.println("\n");
        }
        for (int e : roues.getDijkstra().getparents()) {
        	System.out.println(e + " ");
        }
        
        System.out.println("Passons au choses sérieuses \n");
        for (Case e : roues.getDijkstra().getChemin(ct2.getCase(0, 2))) {
        	System.out.println(e.toString());
        }
        */
	}
}
       