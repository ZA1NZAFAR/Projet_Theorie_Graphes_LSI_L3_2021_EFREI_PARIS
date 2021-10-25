package k;

public class Edge implements Comparable<Edge> {
    private Vertex origin;
    private Vertex destination;
    private int value;

    public Vertex getOrigin() {
        return origin;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getValue() {
        return value;
    }

    public Edge(Vertex origin, Vertex destination, int value) {
        this.origin = origin;
        this.destination = destination;
        this.value = value;
    }

    @Override
    public String toString() {
        return origin + " " + value + " " + destination;
    }

    @Override
    public int compareTo(Edge o) {
        // compare two instance of `Score` and return `int` as result.
        int equalOrigin = this.getOrigin().getName().compareTo(o.getOrigin().getName());

        if (equalOrigin == 0) {
            return this.getDestination().getName().compareTo(o.getDestination().getName());
        }

        return equalOrigin;
    }
}
