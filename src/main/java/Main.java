import exception.CircuitDetectedException;
import lombok.Getter;
import lombok.Setter;
import objects.Automate;
import objects.Etat;
import objects.Tools;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Automate automateOG = new Automate();
        boolean uneEntree, uneSortie;

        automateOG.read("graphe.txt");
        automateOG.display();

        try {
            List<List<Etat>> ranks = Tools.getRanks(automateOG);
            uneEntree = ranks.get(0).size() == 1;
            uneSortie = ranks.get(ranks.size() - 1).size() == 1;
            Tools.displayRanks(ranks);


            System.out.println("Une seule entree? = " + uneEntree);
            System.out.println("Une seule sortie? = " + uneSortie);

            automateOG.read("graphe.txt");
            if (uneEntree && uneSortie) {
                Tools.calculateDatesPlusTot(ranks, automateOG);
                Tools.calculateDatePluTard(ranks, automateOG);
            } else {
                System.out.println("Unable to calculate shortest path!");
            }
            Tools.displayDates(automateOG);
        } catch (CircuitDetectedException exception) {
            System.out.println("ERROR! Le graphe contient un circuit!");
        }
    }
}


