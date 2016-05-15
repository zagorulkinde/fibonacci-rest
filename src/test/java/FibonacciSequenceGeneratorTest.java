import com.emc.fibonacci.FibonacciSequenceGenerator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;

import static java.util.Arrays.asList;
import static org.testng.Assert.assertEquals;

public class FibonacciSequenceGeneratorTest {
    private FibonacciSequenceGenerator fibonacciSequenceGenerator;
    private LinkedList<Long> accumulator = new LinkedList<>();

    @BeforeMethod
    public void setUp() {
        fibonacciSequenceGenerator = new FibonacciSequenceGenerator(accumulator);
    }

    @Test
    public void testCompute() {
        assertEquals(fibonacciSequenceGenerator.compute(1), asList(0L));
        assertEquals(fibonacciSequenceGenerator.compute(2), asList(0L, 1L));
        assertEquals(fibonacciSequenceGenerator.compute(3), asList(0L, 1L, 1L));
    }

}








