package entity;

import java.util.List;

public class Path {

    private int weight;
    private String stringRepresentation;
    private List<Edge> edges;

    public Path(int weight, String stringRepresentation, List<Edge> edges) {
        this.weight = weight;
        this.stringRepresentation = stringRepresentation;
        this.edges = edges;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    public void setStringRepresentation(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
