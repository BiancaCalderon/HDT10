public class Edge {
    //arco
    private Node destination;
    private int weight;

    public Edge(Node destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public Node getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
