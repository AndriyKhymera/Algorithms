package entity;

public class Edge {

    private int weight;
    private int srcVertex;
    private int destVertex;

    public Edge(int srcVertex, int destVertex, int weight) {
        this.srcVertex = srcVertex;
        this.destVertex = destVertex;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "entity.Edge{" +
                "weight=" + weight +
                ", srcVertex=" + srcVertex +
                ", destVertex=" + destVertex +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (weight != edge.weight) return false;
//        if (srcVertex == edge.destVertex && destVertex == edge.srcVertex && oriented == false) return true;
        if (srcVertex != edge.srcVertex) return false;
        return destVertex == edge.destVertex;
    }

    @Override
    public int hashCode() {
        int result = weight;
        result = 31 * result + srcVertex;
        result = 31 * result + destVertex;
        return result;
    }

    //Getters and setters

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSrcVertex() {
        return srcVertex;
    }

    public void setSrcVertex(int srcVertex) {
        this.srcVertex = srcVertex;
    }

    public int getDestVertex() {
        return destVertex;
    }

    public void setDestVertex(int destVertex) {
        this.destVertex = destVertex;
    }
}
