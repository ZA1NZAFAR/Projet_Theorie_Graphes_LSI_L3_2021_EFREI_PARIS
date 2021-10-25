package objects;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Automate automate = new Automate();
        automate.read("graphe.txt");
        automate.display();
        System.out.println("das");
    }
}
