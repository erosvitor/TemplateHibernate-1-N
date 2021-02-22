package com.ctseducare.selects;

import java.util.Iterator;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.ctseducare.hibernate.HibernateUtils;
import com.ctseducare.model.Editora;
import com.ctseducare.model.Livro;

public class EditorasComLivros {

  public static void main(String[] args) {
    listarEditorasComLivrosSQL();
    System.out.println("");
    
    listarEditorasComLivrosHQL();
    System.out.println("");
    
    listarEditorasComLivrosCriteria();
  }
  
  //----------------------------------------------------------------------------------------------------
  // Usando SQL nativo
  //----------------------------------------------------------------------------------------------------
  private static void listarEditorasComLivrosSQL() {
    Session session = null;
    try {
      session = HibernateUtils.getSessionFactory().openSession();
      
      String sql = "SELECT e.codigo, e.nome, l.titulo FROM editoras e INNER JOIN livros l ON (l.codigo_editora = e.codigo)";
      
      {
        // Opção 1
        @SuppressWarnings("unchecked")
        List<Object[]> editoras = session.createNativeQuery(sql).list();
        for (Object[] editora : editoras) {
          System.out.println((Integer)editora[0] + ", " + (String)editora[1] + ", " + (String)editora[2]); 
        }
      }
      
      {
        // Opção 2 (Esta opção faz com que o Hibernate faça uma consulta a tabela livros toda vez que for listar os livros da editora)
        /*
        List<Editora> editoras = session.createNativeQuery(sql, Editora.class).list();
        for (Editora editora : editoras) {
          System.out.println(editora.getCodigo() + ", " + editora.getNome());
          System.out.println("  Livros: ");
          Iterator<Livro> iterator = editora.getLivros().iterator();
          while (iterator.hasNext()) {
            Livro livro = iterator.next();
            System.out.println("    " + livro.getTitulo());
          }
        }
        */
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
  private static void listarEditorasComLivrosHQL() {
    Session session = null;
    try {
      session = HibernateUtils.getSessionFactory().openSession();
      /*
      {
        // Opção 1 (Esta opção faz com que o Hibernate faça uma consulta a tabela livros toda vez que for listar os livros da editora)
        String hql = "FROM Editora";
        TypedQuery<Editora> query = session.createQuery(hql, Editora.class);
      
        List<Editora> editoras = query.getResultList();
        for (Editora editora : editoras) {
          System.out.println(editora.getCodigo() + ", " + editora.getNome()); 
          System.out.println("  Livros: ");
          Iterator<Livro> iterator = editora.getLivros().iterator();
          while (iterator.hasNext()) {
            Livro livro = iterator.next();
            System.out.println("    " + livro.getTitulo());
          }
        }
      }
      */
      {
        // Opção 2 (O uso do 'FETCH' faz com que o Hibernate faça uma única consulta ao banco de dados) 
        String hql = "SELECT e FROM Editora e INNER JOIN FETCH e.livros";
        TypedQuery<Editora> query = session.createQuery(hql, Editora.class);
        List<Editora> editoras = query.getResultList();
        for (Editora editora : editoras) {
          System.out.println(editora.getCodigo() + ", " + editora.getNome());
          System.out.println("  Livros: ");
          Iterator<Livro> iterator = editora.getLivros().iterator();
          while (iterator.hasNext()) {
            Livro livro = iterator.next();
            System.out.println("    " + livro.getTitulo());
          }
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
  // Usando criteria
  //----------------------------------------------------------------------------------------------------
  private static void listarEditorasComLivrosCriteria() {
    // ???
  }

}
