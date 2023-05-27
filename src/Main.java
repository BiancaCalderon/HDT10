import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Leer el grafo del archivo .txt
        Graph graph = ReadFile.readGraph("logistica.txt");

        // Crear instancia de FloydWarshall
        FloydWarshall floydWarshall = new FloydWarshall(graph.getAdjacencyMatrix(), graph.getPaths(), graph.getSize());

        // Calcular rutas más cortas
        floydWarshall.calculateShortestPaths();

        // Main
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Opciones:");
            System.out.println("1. Calcular ruta más corta");
            System.out.println("2. Salir");
            System.out.print("Selecciona una opción: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Ingrese el nodo de origen: ");
                    String sourceNode = scanner.next();
                    System.out.print("Ingrese el nodo de destino: ");
                    String destinationNode = scanner.next();
                    Node source = graph.getNode(sourceNode);
                    Node destination = graph.getNode(destinationNode);
                    if (source != null && destination != null) {
                        int distance = floydWarshall.getDistance(source, destination);
                        if (distance != Integer.MAX_VALUE) {
                            System.out.println("La distancia más corta entre " + sourceNode + " y " + destinationNode + " es: " + distance);
                            List<Node> shortestPath = floydWarshall.getShortestPath(source, destination);
                            System.out.println("Ruta más corta: " + shortestPath);
                        } else {
                            System.out.println("No existe una ruta entre " + sourceNode + " y " + destinationNode);
                        }
                    } else {
                        System.out.println("Uno o ambos nodos no existen en el grafo.");
                    }
                    break;
                case 2:
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }

            System.out.println();
        }

        System.out.println("Adiós!");
    }
}
