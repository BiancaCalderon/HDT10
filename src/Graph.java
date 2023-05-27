import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<String, Node> nodes;
    private int[][] adjacencyMatrix;
    private String[][] paths;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    public void addNode(Node node) {
        nodes.put(node.getName(), node);
    }

    public Node getNode(String cityName) {
        return nodes.get(cityName);
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void generateAdjacencyMatrix() {
        int size = nodes.size();
        adjacencyMatrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    adjacencyMatrix[i][j] = 0;
                } else {
                    Node sourceNode = getNodeByIndex(i);
                    Node destinationNode = getNodeByIndex(j);
                    adjacencyMatrix[i][j] = getDistanceBetweenNodes(sourceNode, destinationNode);
                }
            }
        }
    }

    public String[][] getPaths() {
        return paths;
    }

    public void generatePaths() {
        int size = nodes.size();
        paths = new String[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j) {
                    paths[i][j] = nodes.get(i).getName();
                } else {
                    Node sourceNode = getNodeByIndex(i);
                    Node destinationNode = getNodeByIndex(j);
                    paths[i][j] = findIntermediateNode(sourceNode, destinationNode);
                }
            }
        }
    }

    private String findIntermediateNode(Node sourceNode, Node destinationNode) {
        List<Node> intermediateNodes = new ArrayList<>();
        intermediateNodes.add(sourceNode);

        if (sourceNode.equals(destinationNode)) {
            return "";
        }

        while (!intermediateNodes.isEmpty()) {
            List<Node> newIntermediateNodes = new ArrayList<>();
            for (Node intermediateNode : intermediateNodes) {
                List<Edge> edges = intermediateNode.getEdges();
                for (Edge edge : edges) {
                    Node neighborNode = edge.getDestination();
                    if (neighborNode.equals(destinationNode)) {
                        return intermediateNode.getName();
                    }
                    if (!neighborNode.isVisited()) {
                        neighborNode.setVisited(true);
                        newIntermediateNodes.add(neighborNode);
                    }
                }
            }
            intermediateNodes = newIntermediateNodes;
        }

        return "";
    }

    private Node getNodeByIndex(int index) {
        for (Node node : nodes.values()) {
            if (node.getIndex() == index) {
                return node;
            }
        }
        return null;
    }

    private int getDistanceBetweenNodes(Node sourceNode, Node destinationNode) {
        List<Edge> edges = sourceNode.getEdges();
        for (Edge edge : edges) {
            if (edge.getDestination().equals(destinationNode)) {
                return edge.getWeight();
            }
        }
        return Integer.MAX_VALUE;
    }

    public int getSize() {
        return nodes.size();
    }

    public List<Node> getAdjacentNodes(Node nodeA) {
        return null;
    }
}

