package AlgorithmImpl;

import entity.Edge;

import java.util.Comparator;

public class EdgeWeightComparator implements Comparator<Edge>{
    @Override
    public int compare(Edge edge1, Edge edge2) {
        return Integer.compare(edge1.getWeight(), edge2.getWeight());
    }
}
