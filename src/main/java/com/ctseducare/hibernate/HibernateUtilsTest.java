package com.ctseducare.hibernate;

import org.hibernate.Session;

public class HibernateUtilsTest {

  public static void main(String[] args) {
    Session session = null;
    try {
      session = HibernateUtils.getSessionFactory().openSession();
      System.out.println("Hibernate OK");
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (session != null) {
      session.close();
    }
    HibernateUtils.shutdown();
  }

}
