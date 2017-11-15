package entity;

import java.util.HashSet;
import java.util.Set;

public class Component {

    private Set<Edge> edges;
    private Set<Integer> vertices;

    public Component(Set<Edge> edges) {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();

        for (Edge edge: edges){
            this.addEdgeToComponent(edge);
        }
    }

    public Component(int initVertex) {
        this.edges = new HashSet<>();
        this.vertices = new HashSet<>();
        this.vertices.add(initVertex);
    }

    @Override
    public String toString() {
        return "entity.Component{" +
                "edges=" + edges +
                ", vertices=" + vertices +
                '}';
    }

    public Set<Integer> getVertices() {
        return vertices;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void addEdgeToComponent(Edge edge) {
        this.edges.add(edge);
        this.vertices.add(edge.getSrcVertex());
        this.vertices.add(edge.getDestVertex());
    }

    public void mergeWithComponent(Component component) {
        for (Edge edge : component.getEdges()) {
            this.addEdgeToComponent(edge);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Component component = (Component) o;

        if (edges != null ? !edges.equals(component.edges) : component.edges != null) return false;
        return vertices != null ? vertices.equals(component.vertices) : component.vertices == null;
    }

    @Override
    public int hashCode() {
        int result = edges != null ? edges.hashCode() : 0;
        result = 31 * result + (vertices != null ? vertices.hashCode() : 0);
        return result;
    }
}
