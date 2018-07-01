package implementation;

import utils.MatrixUtils;
import entity.DijkstraLabel;
import entity.Edge;
import entity.Graph;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class DijkstraImplementation {

    private static Set<Integer> visitedVerices;
    private static Set<Integer> unvisitedVertices;
    private static Set<DijkstraLabel> vertexData;

    static {
        visitedVerices = new HashSet<>();
        unvisitedVertices = new HashSet<>();
        vertexData = new HashSet<>();
    }

    public static void main(String[] args) throws FileNotFoundException {
        List weightMatrix = MatrixUtils
                .readMatrixFromFile("/home/andrii/IdeaProjects/KryvyiLabs/Algoritms/data/lab2_matrix_Eulers");
        Graph graph = new Graph(weightMatrix);

        int startVertex = 0;
        int endVertex = 3;
        System.out.println(DijkstraImplementation.findMinimumPathBetween(startVertex, endVertex, graph));
    }


    public static String findMinimumPathBetween(int startVertex, int endVertex, Graph graph) {
        initFields(startVertex, graph);
        int currentVertex;
        while (unvisitedVertices.size() != 0) {
            currentVertex = findNextVertex();
            updateNeighborVerticesInfo(currentVertex, graph);
            visitedVerices.add(currentVertex);
            unvisitedVertices.remove(currentVertex);
        }

        DijkstraLabel dijkstraLabel = getLabelByVertex(endVertex);
        StringBuilder path = new StringBuilder("");
        path.append(endVertex);
        currentVertex = endVertex;

        while (currentVertex != startVertex) {
            currentVertex = dijkstraLabel.getPreviousVertex();
            path.append(currentVertex);
            dijkstraLabel = getLabelByVertex(currentVertex);
        }
        return path.reverse().toString();
    }

    private static void updateNeighborVerticesInfo(int currentVertex, Graph graph) {
        List<Edge> edges = findAllEdgesBySrcVertex(currentVertex, graph);
        DijkstraLabel currentVertexInfo = getLabelByVertex(currentVertex);

        edges.forEach(edge -> {
            DijkstraLabel neighborVertexInfo = getLabelByVertex(edge.getDestVertex());

            int newPathWeight = edge.getWeight() + currentVertexInfo.getShortestPathWeight();
            if (newPathWeight < neighborVertexInfo.getShortestPathWeight()) {
                neighborVertexInfo.setShortestPathWeight(newPathWeight);
                neighborVertexInfo.setPreviousVertex(currentVertex);
            }
        });
    }

    private static List<Edge> findAllEdgesBySrcVertex(int srcVertex, Graph graph) {
        return graph.getEdges().stream().filter(edge -> edge.getSrcVertex() == srcVertex).collect(Collectors.toList());
    }

    private static DijkstraLabel getLabelByVertex(int vertex) {
        return vertexData.stream().filter(dijkstraLabel -> dijkstraLabel.getVertex() == vertex).findFirst().get();
    }

    private static int findNextVertex() {
        return vertexData.stream().filter(vertexData -> !visitedVerices.contains(vertexData.getVertex()))
                .min(new DikstraLabelMinPathComparator()).get().getVertex();
    }

    private static void initFields(int startVertex, Graph graph) {
        graph.getVertices().forEach(vertex -> vertexData.add(new DijkstraLabel(vertex, Integer.MAX_VALUE)));
        vertexData.stream().filter(label -> label.getVertex() == startVertex).findFirst().get().setShortestPathWeight(0);
        unvisitedVertices.addAll(graph.getVertices());
    }
}
