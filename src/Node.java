import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private List<Edge> edges;
    private int index;
    private boolean visited;

    public Node(String name) {
        this.name = name;
        this.edges = new ArrayList<>();
        this.index = index;
        this.visited = false;
    }

    public String getName() {
        return name;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getIndex() {
        return index;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
