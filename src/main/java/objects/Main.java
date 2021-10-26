package objects;

import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Automate automate = new Automate();
        automate.read("graphe.txt");
        automate.display();
        List<List<Etat>> l = new ArrayList<>();
        l.add(automate.getEtats0());
        for (int i = 1; i < automate.getA().size(); i++) {
            HashSet<Etat> tmp = new HashSet<>();
            for (Etat e : l.get(i - 1)) {
                tmp.addAll(e.getAllSuccessors());
            }
            l.add(new ArrayList<>(tmp));
        }
        System.out.println("das");

    }
}
