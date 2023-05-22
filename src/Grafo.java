import java.util.Arrays;

public class Grafo {
    private int V; // Número de vértices
    private int[][] matrizAdy; // Matriz de adyacencia

    public Grafo(int V) {
        this.V = V;
        matrizAdy = new int[V][V];
        // Inicializar matriz de adyacencia con valores infinitos (representando la ausencia de conexión)
        for (int i = 0; i < V; i++) {
            Arrays.fill(matrizAdy[i], Integer.MAX_VALUE);
        }
    }

    public void agregarArco(int origen, int destino, int peso) {
        matrizAdy[origen][destino] = peso;
    }

    public void eliminarArco(int origen, int destino) {
        matrizAdy[origen][destino] = Integer.MAX_VALUE;
    }

    public int[][] algoritmoFloyd() {
        int[][] distancias = new int[V][V];
        // matriz de distancias con valores de la matriz de adyacencia
        for (int i = 0; i < V; i++) {
            System.arraycopy(matrizAdy[i], 0, distancias[i], 0, V);
        }

        //lgoritmo de Floyd
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (distancias[i][k] != Integer.MAX_VALUE && distancias[k][j] != Integer.MAX_VALUE
                            && distancias[i][k] + distancias[k][j] < distancias[i][j]) {
                        distancias[i][j] = distancias[i][k] + distancias[k][j];
                    }
                }
            }
        }

        return distancias;
    }

    ...
}

