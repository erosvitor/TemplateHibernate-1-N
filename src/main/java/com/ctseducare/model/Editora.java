package com.ctseducare.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "editoras")
public class Editora implements java.io.Serializable {

  private static final long serialVersionUID = -2687082317975336124L;

  private Integer codigo;
  private String nome;
  private Set<Livro> livros = new HashSet<>(0);

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo", unique = true, nullable = false)
  public Integer getCodigo() {
    return this.codigo;
  }

  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  }

  @Column(name = "nome", nullable = false, length = 60)
  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "editora", cascade = CascadeType.ALL, orphanRemoval = true)
  public Set<Livro> getLivros() {
    return livros;
  }

  public void setLivros(Set<Livro> livros) {
    this.livros = livros;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }  
    if (obj == null) {
      return false;
    }  
    if (getClass() != obj.getClass()) {
      return false;
    }  
    Editora other = (Editora) obj;
    if (codigo == null) {
      if (other.codigo != null) {
        return false;
      }  
    } else if (!codigo.equals(other.codigo)) {
      return false;
    }  
    return true;
  }
  
}