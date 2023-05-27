import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FloydWarshall {
    private int[][] distances;
    private String[][] paths;
    private int size;
    private HashMap<Object, Object> nodes;

    public FloydWarshall(int[][] adjacencyMatrix, String[][] paths, int size) {
        this.distances = adjacencyMatrix;
        this.paths = paths;
        this.size = size;
    }

    public int[][] getDistances() {
        return distances;
    }

    public String[][] getPaths() {
        return paths;
    }

    public int getSize() {
        return size;
    }

    public void calculateShortestPaths() {
        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE
                            && distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                        paths[i][j] = paths[i][k];
                    }
                }
            }
        }
    }

    public void calculateCenterOfGraph() {
        int[] maxDistances = new int[size];

        for (int i = 0; i < size; i++) {
            int maxDistance = 0;
            for (int j = 0; j < size; j++) {
                if (distances[i][j] > maxDistance) {
                    maxDistance = distances[i][j];
                }
            }
            maxDistances[i] = maxDistance;
        }

        int minMaxDistanceIndex = 0;
        int minMaxDistance = maxDistances[0];

        for (int i = 1; i < size; i++) {
            if (maxDistances[i] < minMaxDistance) {
                minMaxDistance = maxDistances[i];
                minMaxDistanceIndex = i;
            }
        }

        System.out.println("El centro del grafo es el nodo: " + paths[minMaxDistanceIndex][minMaxDistanceIndex]);
    }

    public int getDistance(Node source, Node destination) {
        int sourceIndex = getIndex(source);
        int destinationIndex = getIndex(destination);
        return distances[sourceIndex][destinationIndex];
    }


    public List<Node> getShortestPath(Node source, Node destination) {
        int sourceIndex = getIndex(source);
        int destinationIndex = getIndex(destination);

        if (paths[sourceIndex][destinationIndex] == null) {
            return new ArrayList<>(); // No hay ruta disponible
        }

        List<Node> shortestPath = new ArrayList<>();
        shortestPath.add(source);

        String intermediate = paths[sourceIndex][destinationIndex];
        while (!intermediate.equals(destination.getName())) {
            Node intermediateNode = getNode(intermediate);
            shortestPath.add(intermediateNode);
            intermediate = paths[getIndex(intermediateNode)][destinationIndex];
        }

        shortestPath.add(destination);

        return shortestPath;
    }

    private Node getNode(String nodeName) {
        for (Object node : nodes.values()) {
            if (node.getClass().equals(nodeName)) {
                return (Node) node;
            }
        }
        return null;
    }

    private int getIndex(Node node) {
        int index = 0;
        for (Object n : nodes.values()) {
            if (n == node) {
                return index;
            }
            index++;
        }
        return -1; //No se encontró Node
    }
}
