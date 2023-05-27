import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static Graph readGraph(String filePath) {
        Graph graph = new Graph();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                String city1 = values[0];
                String city2 = values[1];
                int timeNormal = Integer.parseInt(values[2]);
                int timeRain = Integer.parseInt(values[3]);
                int timeSnow = Integer.parseInt(values[4]);
                int timeStorm = Integer.parseInt(values[5]);

                Node node1 = graph.getNode(city1);
                Node node2 = graph.getNode(city2);

                if (node1 == null) {
                    node1 = new Node(city1);
                    graph.addNode(node1);
                }

                if (node2 == null) {
                    node2 = new Node(city2);
                    graph.addNode(node2);
                }

                node1.addEdge(new Edge(node2, timeNormal));
                node1.addEdge(new Edge(node2, timeRain));
                node1.addEdge(new Edge(node2, timeSnow));
                node1.addEdge(new Edge(node2, timeStorm));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}
