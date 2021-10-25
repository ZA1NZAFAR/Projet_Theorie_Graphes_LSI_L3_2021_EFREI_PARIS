package objects;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Automate {
    private final LinkedHashSet<Character> A = Sets.newLinkedHashSet();
    private final LinkedHashSet<String> I = Sets.newLinkedHashSet();
    private final LinkedHashSet<String> T = Sets.newLinkedHashSet();
    private List<Etat> etats = new ArrayList<>();

    public void readFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();

        int alphabetCount = Integer.parseInt(line);
        char character = 'a';
        // Alphabet - A
        for (int i = 0; i < alphabetCount; i++) {
            A.add(character++);
        }

        // Sommets - Q
        line = br.readLine();
        int sommetCount = Integer.parseInt(line);

        for (int i = 1; i <= sommetCount; i++) {
            etats.add(new Etat(i + "", false, false, new ArrayList<>()));
        }


        // Etats initiales - I
        line = br.readLine();
        String[] values = line.split("\\s");

        for (int i = 1; i <= Integer.parseInt(values[0]); i++) {
            I.add(values[i]);
        }

        // Etats terminales - T
        line = br.readLine();
        values = line.split("\\s");

        for (int i = 1; i <= Integer.parseInt(values[0]); i++) {
            T.add(values[i]);
        }

        // Transition - E
        line = br.readLine();
        do {
            values = line.split("\\s");
            String depart = values[0];
            char value = values[1].charAt(0);
            String arrivee = values[2];

            if (!etats.get(etats.indexOf(getEtatFromVal(depart))).hasSuccesseur(new Transition(value, getEtatFromVal(depart), getEtatFromVal(arrivee))))
                etats.get(etats.indexOf(getEtatFromVal(depart))).getTransitions().add(new Transition(value, getEtatFromVal(depart), getEtatFromVal(arrivee)));

            line = br.readLine();
        } while (line != null);
        updateTermInit();
        br.close();
    }

    public void display() {
        System.out.println("Etat initiaux : " + I + "\nEtat Terminaux :" + T);

        String indent = String.format("%-" + (A.size() * 3) + "s", "");

        System.out.print("Etats" + indent.substring(0, indent.length() - "Etats".length()));
        for (char a : A) {
            System.out.print(a + indent.substring(0, indent.length() - 1));
        }
        System.out.println("");
        for (Etat e : etats) {
            System.out.print(e.nom + indent.substring(0, indent.length() - 1));
            for (char a : A) {
                String temp = e.getListOfSuccessors(a).stream().map(etat -> etat.nom).collect(Collectors.toList()).toString();
                System.out.print(temp + indent.substring(0, indent.length() - temp.length()));
            }
            System.out.println();
        }
    }


    public Etat getEtatFromVal(String val) {
        for (Etat e : etats) {
            if (e.nom.equals(val))
                return e;
        }
        return null;
    }

    void updateTermInit() {
        for (String s : I)
            etats.get(etats.indexOf(getEtatFromVal(s))).setInitial(true);
        for (String s : T)
            etats.get(etats.indexOf(getEtatFromVal(s))).setTerminal(true);
    }

}
