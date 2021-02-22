package com.ctseducare.crud;

import com.ctseducare.dao.AutorDAO;
import com.ctseducare.dao.EditoraDAO;
import com.ctseducare.dao.LivroDAO;
import com.ctseducare.model.Autor;
import com.ctseducare.model.Editora;
import com.ctseducare.model.Livro;

public class InsertTest {

  public static void main(String[] args) {
    
    //--------------------------------------------------------------------------------
    // Inserindo um autor
    //--------------------------------------------------------------------------------
    Autor autor = new Autor();
    autor.setNome("Herbert Schildt");
    autor.setEmail("herbert@gmail.com");
    autor.setTelefone("998984444");

    AutorDAO autorDAO = new AutorDAO();
    try {
      autorDAO.insert(autor);
    } catch (Exception e) {
      e.printStackTrace();
    }

    //--------------------------------------------------------------------------------
    // Inserindo uma editora
    //--------------------------------------------------------------------------------
    Editora editora = new Editora();
    editora.setNome("Makron Books");
    
    EditoraDAO editoraDAO = new EditoraDAO();
    try {
      editoraDAO.insert(editora);
    } catch (Exception e) {
      e.printStackTrace();
    }

    //--------------------------------------------------------------------------------
    // Inserindo um livro
    //--------------------------------------------------------------------------------
    Livro livro = new Livro();
    livro.setTitulo("C Completo e Total");
    livro.setEditora(editora);
    livro.setAutor(autor);
    livro.setEdicao((short)3);
    livro.setPreco(320.00);
    
    LivroDAO livroDAO = new LivroDAO();
    try {
      livroDAO.insert(livro);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    System.out.println("Livro inserido com sucesso!");
  }

}
