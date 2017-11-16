package AlgorithmImpl;

import Utils.MatrixUtils;
import entity.Edge;
import entity.Graph;
import entity.Path;

import java.io.CharArrayReader;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class PostmanAlgorithm {

    public static void main(String[] args) {
        List weightMatrix = null;
        Graph graph;
        try {
            weightMatrix = MatrixUtils
                    .readMatrixFromFile("/home/andrii/IdeaProjects/KryvyiLabs/Algoritms/data/lab2_matrix_Eulers");
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

        List<Integer> oddVertices = getOddVertices(eulerianGraph);

        List<Path> possibleVariantsWeights = new ArrayList<>();
        int oddVerticesAmount = oddVertices.size();
        int srcVertex;
        int destVertex;
        //find all pairs
        for (int i = 0; i < oddVerticesAmount; i++) {
            srcVertex = eulerianGraph.getVertices().get(i);
            for (int j = i; j < oddVerticesAmount; j++) {
                destVertex = eulerianGraph.getVertices().get(j);
                possibleVariantsWeights.add(findMinimumWeightPath(srcVertex, destVertex, eulerianGraph));
            }
        }

        int[] sums = new int[oddVerticesAmount / 2];

        //merge pairs sum
        int minSum = possibleVariantsWeights.get(0).getWeight() + possibleVariantsWeights.get(oddVerticesAmount - 0).getWeight();
        int index = 0;
        for (int i = 1; i < oddVerticesAmount / 2; i++) {
//            possibleVariantsWeights.get(i).getEdges().addAll(possibleVariantsWeights.get(oddVerticesAmount - i).getEdges());
//            possibleVariantsWeights.get(i).setWeight(possibleVariantsWeights.get(i).getWeight() + possibleVariantsWeights.get(oddVerticesAmount - i).getWeight());
//            possibleVariantsWeights.remove(possibleVariantsWeights.get(oddVerticesAmount - i));
            sums[i] = possibleVariantsWeights.get(i).getWeight() + possibleVariantsWeights.get(oddVerticesAmount - i).getWeight();
            if (sums[i] < minSum) {
                minSum = sums[i];
                index = i;
            }
        }

        Arrays.stream(sums).min().getAsInt();

        doubleAllEdgesInThePath(possibleVariantsWeights.get(index), eulerianGraph);
        doubleAllEdgesInThePath(possibleVariantsWeights.get(possibleVariantsWeights.size() - index), eulerianGraph);

        return eulerianGraph;
    }

    private static void doubleAllEdgesInThePath(Path path, Graph graph) {
        path.getEdges().forEach(edge -> {
            graph.addEdge(edge);
            graph.addEdge(new Edge(edge.getDestVertex(), edge.getSrcVertex(), edge.getWeight()));
        });
    }

    private static List<Integer> getOddVertices(Graph graph) {
        return graph.getVertices().stream()
                .filter(vertex -> graph.getVertexOrder(vertex) % 2 != 0)
                .collect(Collectors.toList());
    }

    private static Path findMinimumWeightPath(int startVertex, int endVertex, Graph graph) {
        String path = DijkstraImplementation.findMinimumPathBetween(startVertex, endVertex, graph);
        Map.Entry<List<Edge>, Integer> result = convertToMapEntry(path, graph);
        return new Path(result.getValue(), path, result.getKey());
    }

    private static Map.Entry<List<Edge>, Integer> convertToMapEntry(String path, Graph graph) {
        List<Edge> edges = new ArrayList<>();
        Integer weight = 0;
        Edge edge;
        for (int i = 0; i < path.length() - 2; i++) {
            edge = graph.getEdge(Integer.parseInt(path.substring(i, i + 1)), Integer.parseInt(path.substring(i + 1, i + 2)));
            edges.add(edge);
            weight += edge.getWeight();
        }
        return new AbstractMap.SimpleEntry<List<Edge>, Integer>(edges, weight);
    }

    private static String findPath(int startingVertex, Graph graph) {
        List<Edge> edges = graph.getEdges();
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

    private static void removeEdge(Edge edgeToDelete, List<Edge> edges) {
//        edges.removeIf(edge -> (edge.getSrcVertex() == edgeToDelete.getSrcVertex() && edge.getDestVertex() == edgeToDelete.getDestVertex()
//                || edge.getSrcVertex() == edgeToDelete.getDestVertex() && edge.getDestVertex() == edgeToDelete.getSrcVertex()));
        edges.remove(edgeToDelete);
//        edges.remove(new Edge(edgeToDelete))
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
