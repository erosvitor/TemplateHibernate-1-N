package com.ctseducare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro implements java.io.Serializable {

  private static final long serialVersionUID = 2128318314504265685L;

  private Integer codigo;
  private Editora editora;
  private String titulo;
  private Autor autor;
  private Short edicao;
  private Double preco;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "codigo", unique = true, nullable = false)
  public Integer getCodigo() {
    return this.codigo;
  }

  public void setCodigo(Integer codigo) {
    this.codigo = codigo;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "codigo_editora", nullable = false)
  public Editora getEditora() {
    return this.editora;
  }

  public void setEditora(Editora editora) {
    this.editora = editora;
  }

  @Column(name = "titulo", nullable = false, length = 100)
  public String getTitulo() {
    return this.titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "codigo_autor", nullable = false)
  public Autor getAutor() {
    return this.autor;
  }

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

  @Column(name = "edicao", nullable = false)
  public Short getEdicao() {
    return this.edicao;
  }

  public void setEdicao(Short edicao) {
    this.edicao = edicao;
  }

  @Column(name = "preco", nullable = false, precision = 8)
  public Double getPreco() {
    return this.preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
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
    Livro other = (Livro) obj;
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