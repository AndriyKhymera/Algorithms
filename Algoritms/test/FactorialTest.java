import implementation.FactorialImpl;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FactorialTest {

    private Logger log = LoggerFactory.getLogger(FactorialTest.class);

    @Test
    public void shouldBeEquals(){
        int result = FactorialImpl.getRecursive(4);
        Assert.assertEquals(24, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException(){
        int result = FactorialImpl.getRecursive(-1);
    }
}
