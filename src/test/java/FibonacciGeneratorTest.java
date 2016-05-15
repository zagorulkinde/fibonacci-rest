import com.emc.fibonacci.FibonacciGenerator;
import com.emc.fibonacci.Memorizer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FibonacciGeneratorTest {
  private FibonacciGenerator fibonacciGenerator;
  private Memorizer memorizer = mock(Memorizer.class);

  @BeforeMethod
  public void setUp() {
    fibonacciGenerator = new FibonacciGenerator(memorizer);
    when(memorizer.getNumber(1)).thenReturn(1L);
    when(memorizer.getNumber(0)).thenReturn(0L);
  }

  @Test
  public void testGenerate() {
    assertEquals(fibonacciGenerator.generate(1), 1);
    assertEquals(fibonacciGenerator.generate(0), 0);
  }

  @Test
  public void testEvaluated() {

  }
}
