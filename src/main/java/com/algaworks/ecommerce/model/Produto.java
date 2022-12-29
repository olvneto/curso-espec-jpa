package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "produto",
        uniqueConstraints = {@UniqueConstraint(name = "unq_nome", columnNames = {"nome"})},
        indexes = {@Index(name = "idx_nome", columnList = "nome")})
public class Produto extends EntidadeBaseInteger {

  @Column(length = 100, nullable = false)
  private String nome;

  @Lob
  private String descricao;

  private BigDecimal preco;

  @Column(name = "data_criacao", updatable = false, nullable = false)
  private LocalDateTime dataCriacao;

  @Column(name = "data_ultima_atualizacao", insertable = false)
  private LocalDateTime dataUltimaAtualizacao;

  @ManyToMany
  @JoinTable(name = "produto_categoria",
          joinColumns = @JoinColumn(name = "produto_id",
                  foreignKey = @ForeignKey(name = "fk_produto_produto_categoria")),
          inverseJoinColumns = @JoinColumn(name = "categoria_id",
                  foreignKey = @ForeignKey(name = "fk_produto_categoria_categoria")))
  private List<Categoria> categorias;

  @OneToOne(mappedBy = "produto")
  private Estoque estoque;

  @Lob
  private byte[] foto;

  @ElementCollection
  @CollectionTable(name = "produto_tag",
          joinColumns = @JoinColumn(name = "produto_id",
                  foreignKey = @ForeignKey(name = "fk_produto_produto_tag")))
  @Column(name = "tag", length = 50, nullable = false)
  private List<String> tags;

  @ElementCollection
  @CollectionTable(name = "produto_atributo",
          joinColumns = @JoinColumn(name = "produto_id",
                  foreignKey = @ForeignKey(name = "fk_produto_produto_atributo")))
  private List<Atributo> atributos;

}
