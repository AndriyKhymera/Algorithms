package AlgorithmImpl;

import Utils.MatrixUtils;
import entity.DijkstraLabel;
import entity.Graph;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraImplementation {

    private static Set<Integer> visitedVerices;
    private static Set<Integer> unvisitedVertices;
    private static Map<Integer, DijkstraLabel> vertexData;

    public static void main(String[] args) throws FileNotFoundException {
        List weightMatrix = MatrixUtils
                .readMatrixFromFile("/home/andrii/IdeaProjects/KryvyiLabs/Algoritms/data/lab2_matrix_Eulers");
        Graph graph = new Graph(weightMatrix);

        int startVertex = 0;
        int endVertex = 3;
        DijkstraImplementation.findMinimumPathBetween(startVertex, endVertex, graph);
    }


    public static String findMinimumPathBetween(int startVertex, int endVertex, Graph graph) {
        //initialization
        vertexData = new HashMap<>();
        graph.getVertices().forEach(vertex -> vertexData.put(vertex, new DijkstraLabel(vertex, Integer.MAX_VALUE)));
        vertexData.get(startVertex).setShortestPathWeight(0);

        System.out.println(vertexData);

        return "";
    }
}
