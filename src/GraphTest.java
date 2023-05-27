import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GraphTest {
    private Graph graph;

    @BeforeEach
    public void setUp() {
        graph = new Graph();

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);

        nodeA.addEdge(new Edge(nodeB, 10));
        nodeA.addEdge(new Edge(nodeC, 5));
        nodeB.addEdge(new Edge(nodeC, 2));
    }

    @Test
    public void testGetNode() {
        Node nodeA = graph.getNode("A");
        Node nodeB = graph.getNode("B");
        Node nodeC = graph.getNode("C");
        Node nodeD = graph.getNode("D");

        Assertions.assertNotNull(nodeA);
        Assertions.assertNotNull(nodeB);
        Assertions.assertNotNull(nodeC);
        Assertions.assertNull(nodeD);
    }

    @Test
    public void testAddNode() {
        Node nodeD = new Node("D");
        graph.addNode(nodeD);

        Node retrievedNode = graph.getNode("D");
        Assertions.assertNotNull(retrievedNode);
        Assertions.assertEquals("D", retrievedNode.getName());
    }

    @Test
    public void testGetAdjacentNodes() {
        Node nodeA = graph.getNode("A");
        Node nodeB = graph.getNode("B");
        Node nodeC = graph.getNode("C");

        List<Node> adjacentNodesA = graph.getAdjacentNodes(nodeA);
        List<Node> adjacentNodesB = graph.getAdjacentNodes(nodeB);
        List<Node> adjacentNodesC = graph.getAdjacentNodes(nodeC);

        Assertions.assertEquals(2, adjacentNodesA.size());
        Assertions.assertEquals(1, adjacentNodesB.size());
        Assertions.assertEquals(0, adjacentNodesC.size());

        Assertions.assertTrue(adjacentNodesA.contains(nodeB));
        Assertions.assertTrue(adjacentNodesA.contains(nodeC));
        Assertions.assertTrue(adjacentNodesB.contains(nodeC));
    }
}
