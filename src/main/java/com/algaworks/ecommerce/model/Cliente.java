package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NamedStoredProcedureQuery(name = "compraram_acima_media", procedureName = "compraram_acima_media",
        parameters = {
                @StoredProcedureParameter(name = "ano", type = Integer.class, mode = ParameterMode.IN)
        },
        resultClasses = Cliente.class
)
@SecondaryTable(name = "cliente_detalhe",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "cliente_id"),
        foreignKey = @ForeignKey(name = "fk_cliente_detalhe_cliente"))
@Entity
@Table(name = "cliente",
        uniqueConstraints = {@UniqueConstraint(name = "unq_cpf", columnNames = {"cpf"})},
        indexes = {@Index(name = "idx_nome", columnList = "nome")})
public class Cliente extends EntidadeBaseInteger {

  @NotBlank
  @Column(length = 100, nullable = false)
  private String nome;

  @NotBlank
  @Column(length = 14, nullable = false)
  private String cpf;

  @ElementCollection
  @CollectionTable(name = "cliente_contato",
          joinColumns = @JoinColumn(name = "cliente_id",
                  foreignKey = @ForeignKey(name = "fk_cliente_cliente_contato")))
  @MapKeyColumn(name = "tipo")
  @Column(name = "descricao")
  private Map<String, String> contatos;

  @Transient
  private String primeiroNome;

    @NotNull
    @Column(table = "cliente_detalhe", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;

    @Past
    @Column(name = "data_nascimento", table = "cliente_detalhe")
    private LocalDateTime dataNascimento;

  @OneToMany(mappedBy = "cliente")
  private List<Pedido> pedidos;

  @PostLoad
  public void configurarPrimeiroNome() {
    if (nome != null && !nome.isBlank()) {
      int index = nome.indexOf(" ");
      if (index != -1) {
        primeiroNome = nome.substring(0, index);
      }
    }
  }
}
