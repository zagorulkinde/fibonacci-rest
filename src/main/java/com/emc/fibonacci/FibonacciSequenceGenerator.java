package com.emc.fibonacci;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FibonacciSequenceGenerator {
    private final Logger logger = LoggerFactory.getLogger(FibonacciSequenceGenerator.class);
    private List<Long> computedNumbers;


    public FibonacciSequenceGenerator(List<Long> computedNumbers) {
        this.computedNumbers = computedNumbers;
        fillInitialData();
    }

    private void fillInitialData() {
        this.computedNumbers.add(0L);
        this.computedNumbers.add(1L);
    }

    private Collection<Long> truncate(final long number) {
        return computedNumbers.stream().limit(number).collect(Collectors.toList());
    }

    public Collection<Long> compute(long number) {
        if (computedNumbers.size() >= (number)) {
            logger.info("{} sequence exists in accumulator", number);
            return truncate(number);
        }

        return updateSequence(number);
    }

    public Collection<Long> updateSequence(long number) {
        long fiboNumber1, fiboNumber2;

        for (long i = 3; i <= number; ++i) {
            fiboNumber1 = computedNumbers.get(computedNumbers.size() - 2);
            fiboNumber2 = computedNumbers.get(computedNumbers.size() - 1);

            long fibonacci = fiboNumber1 + fiboNumber2;
            logger.info("{} fibonacci number added in accumulator", fibonacci);

            computedNumbers.add(fibonacci);
        }

        return truncate(number);
    }

}
