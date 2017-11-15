package AlgorithmImpl;


import entity.DijkstraLabel;

import java.util.Comparator;

public class DikstraLabelMinPathComparator implements Comparator<DijkstraLabel>{

    @Override
    public int compare(DijkstraLabel o1, DijkstraLabel o2) {
        return Integer.compare(o1.getShortestPathWeight(), o2.getShortestPathWeight());
    }
}
