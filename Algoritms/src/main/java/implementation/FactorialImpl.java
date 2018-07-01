package implementation;

public class FactorialImpl {
    public static int getRecursive(int num){
        if (num < 0){
            throw new IllegalArgumentException("Num shouldn't be less than zero");
        }
        if (num == 0){
            return 1;
        }

        return num * getRecursive(num - 1);
    }
}
