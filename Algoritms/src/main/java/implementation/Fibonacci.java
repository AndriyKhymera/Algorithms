package implementation;

import java.util.LinkedList;
import java.util.List;

public class Fibonacci {

    private static List<Integer> resultList;

    public static List<Integer> getIterative(int amountOfNumbers){
        if (amountOfNumbers < 0){
            throw new IllegalArgumentException("Num shouldn't be less than zero");
        }

        if (amountOfNumbers == 0){
            resultList = new LinkedList<>();
            resultList.add(0);
            return resultList;
        }
        if (amountOfNumbers == 1){
            resultList = new LinkedList<>();
            resultList.add(0);
            resultList.add(1);
            return resultList;
        }

        resultList = new LinkedList<>();
        resultList.add(0);
        resultList.add(1);
        int index = 2;

        while (resultList.size() < amountOfNumbers){
            resultList.add(resultList.get(index-1) + resultList.get(index-2));
            index++;
        }
        return resultList;
    }

}
