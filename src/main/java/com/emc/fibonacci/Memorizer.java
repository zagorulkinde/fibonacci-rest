package com.emc.fibonacci;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Memorizer {
  private static Memorizer instance = null;
  private ConcurrentMap<Long, Long> accumulator;

  private Memorizer() {
    accumulator = new ConcurrentHashMap<Long, Long>();
  }

  public static synchronized Memorizer getInstance() {
    if (instance == null) {
      instance = new Memorizer();
    }

    return instance;
  }

  public void putNumber(long number, long result) {
    accumulator.putIfAbsent(number, result);
  }

  public Long getNumber(long number) {
    return accumulator.get(number);
  }

}
