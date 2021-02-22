package com.ctseducare.crud;

import com.ctseducare.dao.AutorDAO;
import com.ctseducare.dao.EditoraDAO;
import com.ctseducare.dao.LivroDAO;
import com.ctseducare.model.Autor;
import com.ctseducare.model.Editora;
import com.ctseducare.model.Livro;

public class SelectTest {

  public static void main(String[] args) {

    //--------------------------------------------------------------------------------
    // Selecionando um autor
    //--------------------------------------------------------------------------------
    AutorDAO autorDAO = new AutorDAO();
    try {
      Autor autor = autorDAO.findById(1);
      System.out.println(autor.getNome());
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    //--------------------------------------------------------------------------------
    // Selecionando uma editora
    //--------------------------------------------------------------------------------
    EditoraDAO editoraDAO = new EditoraDAO();
    try {
      Editora editora = editoraDAO.findById(1);
      System.out.println(editora.getNome());
    } catch (Exception e) {
      e.printStackTrace();
    }

    //--------------------------------------------------------------------------------
    // Selecionando um livro
    //--------------------------------------------------------------------------------
    LivroDAO livroDAO = new LivroDAO();
    try {
      Livro livro = livroDAO.findById(1);
      System.out.println(livro.getTitulo());
      System.out.println(livro.getAutor().getNome());
    } catch (Exception e) {
      e.printStackTrace();
    }

    //--------------------------------------------------------------------------------
    // Selecionando o autor a partir do livro
    //--------------------------------------------------------------------------------
    LivroDAO livroDAO2 = new LivroDAO();
    try {
      Livro livro = livroDAO2.findById(1);
      
      // Aqui vai gerar uma execção
      System.out.println(livro.getAutor().getNome());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
