package com.ctseducare.selects;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.ctseducare.hibernate.HibernateUtils;
import com.ctseducare.model.Editora;

public class TodasEditoras {

  public static void main(String[] args) {
    listarTodasEditorasSQL();
    System.out.println("");
    
    listarTodasEditorasHQL();
    System.out.println("");
    
    listarTodasEditorasCriteria();
  }
  
  //----------------------------------------------------------------------------------------------------
  // Usando SQL nativo
  //----------------------------------------------------------------------------------------------------
  private static void listarTodasEditorasSQL() {
    Session session = null;
    try {
      session = HibernateUtils.getSessionFactory().openSession();
      
      String sql = "SELECT codigo, nome FROM editoras";
      
      {
        // Opção 1
        @SuppressWarnings("unchecked")
        List<Object[]> editoras = session.createNativeQuery(sql).list();
        for (Object[] editora : editoras) {
          System.out.println((Integer)editora[0] + ", " + (String)editora[1]); 
        }
      }
      
      {
        // Opção 2
        List<Editora> editoras = session.createNativeQuery(sql, Editora.class).list();
        for (Editora editora : editoras) {
          System.out.println(editora.getCodigo() + ", " + editora.getNome());
        }
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
  // Usando HQL
  //----------------------------------------------------------------------------------------------------
  private static void listarTodasEditorasHQL() {
    Session session = null;
    try {
      session = HibernateUtils.getSessionFactory().openSession();
      
      String hql = "FROM Editora";
      TypedQuery<Editora> query = session.createQuery(hql, Editora.class);
      
      List<Editora> editoras = query.getResultList();
      for (Editora editora : editoras) {
        System.out.println(editora.getCodigo() + ", " + editora.getNome()); 
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
  // Usando criteria
  //----------------------------------------------------------------------------------------------------
  private static void listarTodasEditorasCriteria() {
    Session session = null;
    try {
      session = HibernateUtils.getSessionFactory().openSession();
      
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Editora> cq = cb.createQuery(Editora.class);
      Root<Editora> root = cq.from(Editora.class);
      cq.select(root);
   
      List<Editora> editoras = session.createQuery(cq).getResultList();
      for (Editora editora : editoras) {
        System.out.println(editora.getCodigo() + ", " + editora.getNome()); 
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

}
