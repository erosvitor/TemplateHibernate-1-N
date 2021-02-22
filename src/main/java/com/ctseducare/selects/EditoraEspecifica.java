package com.ctseducare.selects;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.ctseducare.hibernate.HibernateUtils;
import com.ctseducare.model.Editora;

public class EditoraEspecifica {

  public static void main(String[] args) {
    listarEditoraEspecificaSQL();
    System.out.println("");
    
    listarEditoraEspecificaHQL();
    System.out.println("");
    
    listarEditoraEspecificaCriteria();
  }
  
  //----------------------------------------------------------------------------------------------------
  // Usando SQL nativo
  //----------------------------------------------------------------------------------------------------
  private static void listarEditoraEspecificaSQL() {
    Session session = null;
    try {
      session = HibernateUtils.getSessionFactory().openSession();
      
      String sql = "SELECT codigo, nome FROM editoras WHERE codigo = :codigo";
      
      {
        // Opção 1
        Query query = session.createNativeQuery(sql);
        query.setParameter("codigo", 1);
        
        @SuppressWarnings("unchecked")
        List<Object[]> editoras = query.getResultList();
        for (Object[] editora : editoras) {
          System.out.println((Integer)editora[0] + ", " + (String)editora[1]); 
        }
      }
      
      {
        // Opção 2
        TypedQuery<Editora> query = session.createNativeQuery(sql, Editora.class);
        query.setParameter("codigo", 1);
        
        List<Editora> editoras = query.getResultList();
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
  private static void listarEditoraEspecificaHQL() {
    Session session = null;
    try {
      session = HibernateUtils.getSessionFactory().openSession();
      
      String hql = "FROM Editora WHERE codigo = :codigo";
      TypedQuery<Editora> query = session.createQuery(hql, Editora.class);
      query.setParameter("codigo", 1);
      
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
  private static void listarEditoraEspecificaCriteria() {
    Session session = null;
    try {
      session = HibernateUtils.getSessionFactory().openSession();
      
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Editora> cq = cb.createQuery(Editora.class);
      Root<Editora> root = cq.from(Editora.class);
      cq.select(root).where(cb.equal(root.get("codigo"), 1));
   
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
