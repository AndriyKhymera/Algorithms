package AlgorithmImpl;

import Utils.MatrixUtils;
import entity.Edge;
import entity.Graph;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class PostmanAlgorithm {

    private static final String PAIR_FORMAT = ;

    public static void main(String[] args) {
        List weightMatrix = null;
        Graph graph;
        try {
            weightMatrix = MatrixUtils
                    .readMatrixFromFile("/home/andrii/IdeaProjects/KryvyiLabs/untitled/data/lab2_matrix_Eulers");
            graph = new Graph(weightMatrix);
            System.out.println(graph.toString());

            Scanner scanner = new Scanner(System.in);
            int startVertex;
            System.out.println("Enter vertex to start: ");
            startVertex = scanner.nextInt();

            String path;
            if (!hasEulerianPath(graph)) {
                Graph modifiedGraph = makeEulerian(graph);
                path = findPath(startVertex, modifiedGraph);
            } else {
                path = findPath(startVertex, graph);
            }
            System.out.println("Path: " + path);
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file");
        }
    }

    private static Graph makeEulerian(Graph graph) {
        Graph eulerianGraph = new Graph(graph.getVertices(), graph.getEdges());

        List<Integer> oddVertices = graph.getVertices().stream()
                .filter(vertex -> graph.getVertexOrder(vertex) % 2 != 0)
                .collect(Collectors.toList());

        Map<Edge, Integer> possibleVariantsWeights = new HashMap<>();

        for (int i = 0; i < oddVertices.size(); i++) {
            for (int j = i; j < oddVertices.size(); j++) {
                possibleVariantsWeights.put(new Edge(oddVertices.get(i), oddVertices.get(j), ))
            }
        }

        return eulerianGraph;
    }

    private static List<Edge> findMinimumWeightPath(int startVertex, int endVertex, Graph graph){
        
    }

    private static String findPath(int startingVertex, Graph graph) {
        Set<Edge> edges = graph.getEdges();
        Map<Integer, Integer> verticesOrder = graph.getVertexOrder();
        verticesOrder.put(startingVertex, verticesOrder.get(startingVertex) - 1);

        boolean complete = false;
        List<Integer> path = new LinkedList<>();
        path.add(startingVertex);
        int currentVertex = startingVertex;

        while (!complete) {
            int finalCurrentVertex = currentVertex;
            System.out.println("current vertex: " + currentVertex);
            Optional<Edge> edge = edges.stream()
                    .filter(any -> any.getSrcVertex() == finalCurrentVertex)
                    .filter(e -> verticesOrder.get(e.getDestVertex()) != 1)
                    .findAny();
            if (!edge.isPresent()) {
                edge = edges.stream()
                        .filter(any -> any.getSrcVertex() == finalCurrentVertex)
                        .findAny();
            }
            currentVertex = edge.get().getDestVertex();
            System.out.println("Add vertex to graph: " + currentVertex);
            path.add(currentVertex);

            verticesOrder.put(currentVertex, verticesOrder.get(currentVertex) - 1);

            System.out.println("removing edge: " + edge);
            removeEdge(edge.get(), edges);
            if (currentVertex == startingVertex) {
                complete = true;
            }
        }
        return path.toString();
    }

    private static void removeEdge(Edge edgeToDelete, Set<Edge> edges) {
        edges.removeIf(edge -> (edge.getSrcVertex() == edgeToDelete.getSrcVertex() && edge.getDestVertex() == edgeToDelete.getDestVertex()
                || edge.getSrcVertex() == edgeToDelete.getDestVertex() && edge.getDestVertex() == edgeToDelete.getSrcVertex()));
    }

    private static int countGraphWeight(Graph graph) {
        return graph.getEdges().stream()
                .mapToInt(Edge::getWeight)
                .sum();
    }

    private static boolean hasEulerianPath(Graph graph) {
        long oddVertexAmount = graph.getVertices().stream()
                .filter(vertex -> graph.getVertexOrder(vertex) % 2 != 0)
                .peek(System.out::print)
                .count();
        if (oddVertexAmount == 0) {
            System.out.println("You can start in any vertex you want.");
        }
        if (oddVertexAmount == 2) {
            System.out.println("Choose vertex above to find a path");
        }
        return (oddVertexAmount == 2 || oddVertexAmount == 0);
    }

}
