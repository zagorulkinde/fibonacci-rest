package com.emc.fibonacci;

public class FibonacciGenerator {
  private long fiboNumber1 = 0;
  private long fiboNumber2 = 1;
  private final Memorizer memorizer;

  public FibonacciGenerator(Memorizer memorizer) {
    this.memorizer = memorizer;
  }

  public long generate(long number) {
    long fibonacci = 0;

    if (number <= 1) {
      return number;
    }

    Long evaluated = memorizer.getNumber(number);
    if (evaluated != null) {
      return evaluated;
    }

    return compute(number, fibonacci);
  }

  private long compute(long number, long fibonacci) {
    for (long i = 2; i < number; i++) {
      fibonacci = fiboNumber1 + fiboNumber2;
      memorizer.putNumber(i, fibonacci);
      fiboNumber2 = fiboNumber1;
      fiboNumber1 = fibonacci;
    }

    return fibonacci;
  }

}
