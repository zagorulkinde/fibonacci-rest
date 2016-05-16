import com.emc.fibonacci.FibonacciSequenceGenerator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class FibonacciSequenceGeneratorTest {
    private FibonacciSequenceGenerator fibonacciSequenceGenerator;
    private LinkedList<Long> accumulator = new LinkedList<>();

    private Lock readLock;
    private Lock writeLock;

    @BeforeMethod
    public void setUp() {
        ReadWriteLock mockedLock = mock(ReentrantReadWriteLock.class);

        readLock = mock(ReentrantReadWriteLock.ReadLock.class);
        writeLock = mock(ReentrantReadWriteLock.WriteLock.class);

        when(mockedLock.readLock()).thenReturn(readLock);
        when(mockedLock.writeLock()).thenReturn(writeLock);

        fibonacciSequenceGenerator = new FibonacciSequenceGenerator(accumulator, mockedLock);
    }

    @Test
    public void testCompute() {
        assertEquals(fibonacciSequenceGenerator.compute(1), asList(0L));
        assertEquals(fibonacciSequenceGenerator.compute(2), asList(0L, 1L));
        assertEquals(fibonacciSequenceGenerator.compute(3), asList(0L, 1L, 1L));

        verify(readLock, times(3)).lock();
        verify(writeLock, times(2)).lock();

    }

}








