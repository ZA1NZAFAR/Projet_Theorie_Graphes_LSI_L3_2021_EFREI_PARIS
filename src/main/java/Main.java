import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Graphe graphe = new Graphe();

        try {
            graphe.read("graphe.txt");
        } catch (IOException e) {
            System.out.println("Error while reading graph: " + e.getMessage());
        }

        graphe.display();
        graphe.sort();
    }
}
