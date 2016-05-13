import com.emc.fibonacci.Memorizer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class MemorizerTest {
  private Memorizer memorizer;

  @BeforeMethod
  public void setUp() {
    memorizer = Memorizer.getInstance();
  }

  @Test
  public void testSingleton() {
    assertEquals(memorizer, Memorizer.getInstance());
  }

  @Test
  public void testExistNumber() {
    memorizer.putNumber(1L, 1L);
    assertEquals(memorizer.getNumber(1L), Long.valueOf(1L));
    assertNull(memorizer.getNumber(0));
  }

}
