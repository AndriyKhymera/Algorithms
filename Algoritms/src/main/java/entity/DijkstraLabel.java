package entity;

public class DijkstraLabel {

    private int vertex;
    private int shortestPathWeight;
    private int previousVertex;

    public DijkstraLabel(int vertex, int shortestPathWeight) {
        this.vertex = vertex;
        this.shortestPathWeight = shortestPathWeight;
    }


    @Override
    public String toString() {
        return "DijkstraLabel{" +
                "vertex=" + vertex +
                ", shortestPathWeight=" + shortestPathWeight +
                ", previousVertex=" + previousVertex +
                '}';
    }

    public void setShortestPathWeight(int shortestPathWeight) {
        this.shortestPathWeight = shortestPathWeight;
    }

    public void setPreviousVertex(int previousVertex) {
        this.previousVertex = previousVertex;
    }
}
