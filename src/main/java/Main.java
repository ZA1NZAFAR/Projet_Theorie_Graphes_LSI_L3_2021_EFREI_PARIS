import exception.CircuitDetectedException;
import objects.Automate;
import objects.Etat;
import objects.Tools;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Automate automateOG = new Automate();
        boolean uneEntree, uneSortie;

        automateOG.read("Graphe G5.txt");
        automateOG.display();

        try {
            List<List<Etat>> ranks = Tools.getRanks(automateOG);
            uneEntree = ranks.get(0).size() == 1;
            uneSortie = ranks.get(ranks.size() - 1).size() == 1;
            Tools.displayRanks(ranks);


            System.out.println("Une seule entree? = " + ((uneEntree) ? "Oui" : "Non"));
            System.out.println("Une seule sortie? = " + ((uneSortie) ? "Oui" : "Non"));

            automateOG.read("graphe.txt");
            if (uneEntree && uneSortie) {
                Tools.calculateDatesPlusTot(ranks, automateOG);
                Tools.calculateDatePluTard(ranks, automateOG);
                Tools.displayDates(automateOG);
            } else {
                System.out.println("Impossible de calculer le plus court chemin/calendrier");
            }
        } catch (CircuitDetectedException exception) {
            System.out.println("ERROR! Le graphe contient un circuit!");
        }
    }
}


