import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Graphe {
    private Set<Vertex> vertices = Sets.newLinkedHashSet();
    private List<Edge> edges = Lists.newArrayList();

    public void read(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        int verticesCount = Integer.parseInt(line);

        for (int i = 0; i < verticesCount; i++) {
            Vertex vertex = new Vertex(String.valueOf(i));
            vertices.add(vertex);
        }

        line = br.readLine();
        int edgesCount = Integer.parseInt(line);

        for (int i = 0; i < edgesCount; i++) {
            line = br.readLine();
            String[] edge = line.split("\\s");

            final String originName = edge[0];
            Vertex origin = null;
            Optional<Vertex> opt = vertices.stream().filter(v -> v.getName().equals(originName)).findFirst();
            if (opt.isPresent())
                origin = opt.get();

            final String destinationName = edge[1];
            Vertex destination = null;
            opt = vertices.stream().filter(v -> v.getName().equals(destinationName)).findFirst();
            if (opt.isPresent())
                destination = opt.get();

            int value = Integer.parseInt(edge[2]);
            Edge newEdge = new Edge(origin, destination, value);
            edges.add(newEdge);
        }

        sort();
    }

    public void display() {
        String line = "";
        String indentation = " ";
        Optional<Integer> opt = vertices.stream().map(v -> v.getName().length()).max(Comparator.naturalOrder());
        int length = 0;
        if (opt.isPresent())
            length = opt.get();

        indentation = String.format("%-" + (length + 3) + "s", "");
        String indentationWithVertices = indentation.substring(0, indentation.length() - length);

        line = indentation;
        for (Vertex vertex : vertices)
            line = line + vertex + indentationWithVertices;

        System.out.println(line);
        line = "";

        Iterator<Vertex> vertexIterator = vertices.iterator();
        for (int i = 0; i < vertices.size(); i++) {
            Vertex vertex = vertexIterator.next();
            line = vertex + indentationWithVertices;
            List<Edge> vertexEdges = edges.stream().filter(e -> e.getOrigin().equals(vertex)).collect(Collectors.toList());

            for (Vertex v : vertices) {
                List<Edge> outEdges = vertexEdges.stream().filter(vx -> vx.getDestination().equals(v)).collect(Collectors.toList());
                List<String> values = outEdges.stream().map(e -> String.valueOf(e.getValue())).collect(Collectors.toList());
                String adjacentVertices = String.join(", ", values);
                line = line + ((outEdges.size() == 0) ? indentation : adjacentVertices + indentation.substring(0, indentation.length() - adjacentVertices.length()));
            }

            System.out.println(line);
            line = "";
        }
    }

    public void sort() {
        Collections.sort(edges);
    }
}
