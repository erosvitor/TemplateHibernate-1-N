package com.ctseducare.selects;

import java.math.BigInteger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import com.ctseducare.hibernate.HibernateUtils;
import com.ctseducare.model.Editora;

public class TotalEditoras {

  public static void main(String[] args) {
    listarTotalEditorasSQL();
    System.out.println("");
    
    listarTotalEditorasHQL();
    System.out.println("");

    listarTotalEditorasCriteria();
  }
  
//----------------------------------------------------------------------------------------------------
  // Usando SQL nativo
  //----------------------------------------------------------------------------------------------------
  private static void listarTotalEditorasSQL() {
    Session session = null;
    try {
      session = HibernateUtils.getSessionFactory().openSession();
      
      String sql = "SELECT COUNT(codigo) FROM editoras";
      
      BigInteger total = (BigInteger)session.createNativeQuery(sql).getSingleResult();
      System.out.println("Total editoras: " + total);
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }
  
  //----------------------------------------------------------------------------------------------------
  // Usando HQL
  //----------------------------------------------------------------------------------------------------
  private static void listarTotalEditorasHQL() {
    Session session = null;
    try {
      session = HibernateUtils.getSessionFactory().openSession();
      
      String hql = "SELECT COUNT(codigo) FROM Editora";
      
      {
        Long total = (Long)session.createQuery(hql).list().get(0);
        System.out.println("Total editoras: " + total);
      }
      
      {
        Long total = (Long)session.createQuery(hql).getSingleResult();
        System.out.println("Total editoras: " + total);
      }  
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  //----------------------------------------------------------------------------------------------------
  // Usando Criteria
  //----------------------------------------------------------------------------------------------------
  private static void listarTotalEditorasCriteria() {
    Session session = null;
    try {
      session = HibernateUtils.getSessionFactory().openSession();
      
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Long> cq = cb.createQuery(Long.class);
      cq.select(cb.count(cq.from(Editora.class)));
      
      Long total = session.createQuery(cq).getSingleResult();
      System.out.println("Total editoras: " + total);
      
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

}
