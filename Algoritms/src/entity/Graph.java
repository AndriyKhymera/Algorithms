package entity;

import Utils.MatrixUtils;

import java.util.*;

public class Graph {

    private int vertexAmount;
    private Set<Integer> vertices;
    private Map<Integer, Integer> vertexOrder;
    private Set<Edge> edges;

    public Graph(int vertexAmount) {
        this.vertexAmount = vertexAmount;
        vertices = new HashSet<>();
        edges = new HashSet<>();
        vertexOrder = new HashMap<>();
    }

    public Graph(Set<Integer> vertices, Set<Edge> edges) {
        this.vertexAmount = vertices.size();
        this.vertices = vertices;
        this.edges = edges;
        vertexOrder = new HashMap<>();
    }

    public Graph(List<List<Integer>> weightMatrix) {
        this(weightMatrix.size());
        if (!MatrixUtils.checkForSquare(weightMatrix)) {
            throw new IllegalArgumentException("Input matrix isn't square check the file");
        }

        this.vertexAmount = weightMatrix.size();
        vertexOrder = new HashMap<>();

        int order = 0;
        for (int i = 0; i < weightMatrix.size(); i++) {
            vertices.add(i);
            vertexOrder.put(i, order);
        }

        List<Integer> line;
        int weight;
        for (int i = 0; i < weightMatrix.size(); i++) {
            line = weightMatrix.get(i);
            for (int j = 0; j < line.size(); j++) {
                weight = line.get(j);
                if (weight != 0) {
                    this.addEdge(new Edge(i, j, weight));
                    vertexOrder.put(i, vertexOrder.get(i) + 1);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Graph{" +
                "vertexAmount=" + vertexAmount +
                ", vertices=" + vertices +
                ", vertexOrder=" + vertexOrder +
                ", \nedges=" + edges +
                '}';
    }

    public int getVertexOrder(int vertex) {
        return vertexOrder.get(vertex);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public Set<Integer> getVertices() {
        return vertices;
    }

    public int getVertexAmount() {
        return vertexAmount;
    }

    public Map<Integer, Integer> getVertexOrder() {
        return vertexOrder;
    }
}
