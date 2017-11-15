package AlgorithmImpl;

import Utils.MatrixUtils;
import entity.Component;
import entity.Edge;
import entity.Graph;
import entity.Vertex;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class BoruvkasAlgorithm {

    public static void main(String[] args) {
        try {
            List weightMatrix = MatrixUtils.readMatrixFromFile("/home/andrii/IdeaProjects/KryvyiLabs/untitled/data/lab1_matrix");
            Graph graph = new Graph(weightMatrix);
            System.out.println(graph);
            Graph mstGraph = findMST(graph);
            System.out.println("MST graph " + mstGraph);
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s", e.getCause());
        }
    }

    private static Graph findMST(Graph initialGraph) {
        Set<Component> components = initComponents(initialGraph.getVertexAmount());
        Set<Edge> edges = initialGraph.getEdges();

        int count = 1;
        while (components.size() > 1) {
            System.out.println("**** Step 1 ****");
            Edge minEdgeForComponent;
            for (Component component : components) {
                minEdgeForComponent = findMinimumWeightEdgeForComponent(component, edges);
                component.addEdgeToComponent(minEdgeForComponent);
            }
            removeInnerComponentEdges(components, edges);
            components = mergeComponents(components);

            System.out.println("Current components amount: " + components.size());
        }
        Component component = components.iterator().next();
        return new Graph(component.getVertices(), component.getEdges());
    }

    private static Set<Component> mergeComponents(Set<Component> components) {
        Set<Component> newComponents = new HashSet<>();

        for (Component firstComponent : components) {
            for (Component secondComponent : components) {
                if (!Collections.disjoint(firstComponent.getVertices(), secondComponent.getVertices()) && firstComponent != secondComponent) {
                    Set<Edge> edges = new HashSet<>(firstComponent.getEdges());
                    edges.addAll(secondComponent.getEdges());

                    firstComponent.getVertices().clear();
                    secondComponent.getVertices().clear();

                    firstComponent.getEdges().clear();
                    secondComponent.getEdges().clear();

                    newComponents.add(new Component(edges));
                }
            }
        }

        boolean allAreDifferent = true;
        for (Component firstComponent : newComponents) {
            Set<Integer> vertices = firstComponent.getVertices();
            for (Component secondComponent : newComponents) {
                if (firstComponent == secondComponent) {
                    continue;
                }
                for (int i : secondComponent.getVertices()) {
                    if (vertices.contains(i)) {
                        allAreDifferent = false;
                        break;
                    }
                }
            }
            if (!allAreDifferent) {
                break;
            }
        }

        components.removeIf(component -> component.getEdges().size() == 0);
        if (components.size() != 0 || !allAreDifferent) {
            newComponents.addAll(components);
            newComponents = mergeComponents(newComponents);
        }

        return newComponents;
    }

    private static void removeInnerComponentEdges(Set<Component> components, Set<Edge> edges) {
        for (Component component : components) {
            removeInnerComponentEdges(component, edges);
        }
    }

    private static void removeInnerComponentEdges(Component component, Set<Edge> edges) {
        Iterator<Edge> iterator = edges.iterator();

        Edge edge;
        while (iterator.hasNext()) {
            edge = iterator.next();
            if (component.getVertices().contains(edge.getSrcVertex()) && component.getVertices().contains(edge.getDestVertex())) {
                iterator.remove();
            }
        }
    }

    private static Edge findMinimumWeightEdgeForComponent(Component component, Set<Edge> edges) {
        System.out.println("Finding minimum weight edge for component: "  + component);
        Edge minEdge = edges.stream()
                .filter(edge -> {
                    int firstComponentVertex = component.getVertices().stream().findFirst().get();
                    return edge.getSrcVertex() == firstComponentVertex || edge.getDestVertex() == firstComponentVertex;
                })
                .findFirst()
                .get();
        EdgeWeightComparator edgeWeightComparator = new EdgeWeightComparator();
        Edge tempEdge;

        for (int vertex : component.getVertices()) {
            tempEdge = findMinimumWeightEdgeForSrcVertex(vertex, edges);
            if (edgeWeightComparator.compare(tempEdge, minEdge) == -1) {
                minEdge = tempEdge;
            }
        }
        System.out.println("Result: " + minEdge);
        return minEdge;
    }

    private static Edge findMinimumWeightEdgeForSrcVertex(int vertex, Set<Edge> edges) {
        Edge minEdge = edges.stream()
                .filter(edge -> edge.getSrcVertex() == vertex || edge.getDestVertex() == vertex)
                .sorted(new EdgeWeightComparator())
                .findFirst()
                .get();
        return minEdge;
    }

    private static Set<Component> initComponents(int verticesAmount) {
        Set<Component> components = new HashSet<>();
        for (int i = 0; i < verticesAmount; i++) {
            components.add(new Component(i));
        }
        return components;
    }
}
