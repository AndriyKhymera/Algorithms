import implementation.Fibonacci;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class FibonacciTest {

    @Test
    public void shouldBeEquals() {
        List<Integer> result;
        List<Integer> expectedResult = Arrays.asList(0);
        Assert.assertEquals(expectedResult, Fibonacci.getIterative(0));

        expectedResult = Arrays.asList(0, 1);
        Assert.assertEquals(expectedResult, Fibonacci.getIterative(2));

        expectedResult = Arrays.asList(0, 1, 1);
        Assert.assertEquals(expectedResult, Fibonacci.getIterative(3));

        expectedResult = Arrays.asList(0, 1, 1, 2);
        Assert.assertEquals(expectedResult, Fibonacci.getIterative(4));

        expectedResult = Arrays.asList(0, 1, 1, 2, 3);
        Assert.assertEquals(expectedResult, Fibonacci.getIterative(5));

        expectedResult = Arrays.asList(0, 1, 1, 2, 3, 5);
        Assert.assertEquals(expectedResult, Fibonacci.getIterative(6));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException() {
        Fibonacci.getIterative(-1);
    }


}
