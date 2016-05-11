package com.emc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SuppressWarnings("unused")
public class Main {
  public static void main(final String[] args) throws Exception {
    ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
  }
}