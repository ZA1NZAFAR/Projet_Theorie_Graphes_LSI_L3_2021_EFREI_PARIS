package k;

import java.util.Objects;

public class Vertex {
    private String name;

    public String getName() {
        return name;
    }

    public Vertex(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vertex))
            return false;

        Vertex vertex = (Vertex) obj;
        return this.name.equals(vertex.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
