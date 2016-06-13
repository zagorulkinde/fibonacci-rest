package com.emc.fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.stream.Collectors;

public class FibonacciSequenceGenerator {
    private final Logger logger = LoggerFactory.getLogger(FibonacciSequenceGenerator.class);
    private final Lock writeLock;
    private final Lock readLock;
    private List<BigInteger> computedNumbers;


    public FibonacciSequenceGenerator(List<BigInteger> computedNumbers, ReadWriteLock lock) {
        this.computedNumbers = computedNumbers;
        this.writeLock = lock.writeLock();
        this.readLock = lock.readLock();

        fillInitialData();
    }

    private void fillInitialData() {
        writeLock.lock();
        try {
            if (computedNumbers.isEmpty()) {
                this.computedNumbers.add(BigInteger.ZERO);
                this.computedNumbers.add(BigInteger.ONE);
            }

        } finally {
            writeLock.unlock();
        }
    }

    private Collection<BigInteger> truncate(final long number) {
        return computedNumbers.stream().limit(number).collect(Collectors.toList());
    }

    public Collection<BigInteger> compute(long number) {
        readLock.lock();
        try {
            if (computedNumbers.size() >= (number)) {
                logger.info("{} sequence exists in accumulator", number);
                return truncate(number);
            }

        } finally {
            readLock.unlock();
        }
        return updateSequence(number);
    }

    private Collection<BigInteger> updateSequence(long number) {
        writeLock.lock();
        try {
            BigInteger fiboNumber1, fiboNumber2;

            for (long i = computedNumbers.size(); i <= number; ++i) {
                fiboNumber1 = computedNumbers.get(computedNumbers.size() - 2);
                fiboNumber2 = computedNumbers.get(computedNumbers.size() - 1);

                BigInteger fibonacci = fiboNumber1.add(fiboNumber2);
                logger.info("{} fibonacci number added in accumulator", fibonacci);

                computedNumbers.add(fibonacci);
            }

            return truncate(number);

        } finally {
            writeLock.unlock();
        }

    }

}
