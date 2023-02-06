package com.algaworks.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "item_pedido-produto.ItemPedido-Produto",
                entities = {@EntityResult(entityClass = ItemPedido.class),
                        @EntityResult(entityClass = Produto.class)})
})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

  @EmbeddedId
  private ItemPedidoId id;

  @Column(name = "preco_produto", nullable = false)
  private BigDecimal precoProduto;

  @Column(nullable = false)
  private Integer quantidade;

  @MapsId("pedidoId")
  @ManyToOne(optional = false)
  @JoinColumn(name = "pedido_id",
          foreignKey = @ForeignKey(name = "fk_item_pedido_pedido"), nullable = false)
  private Pedido pedido;

  @MapsId("produtoId")
  @ManyToOne(optional = false)
  @JoinColumn(name = "produto_id",
          foreignKey = @ForeignKey(name = "fk_item_pedido_produto"), nullable = false)
  private Produto produto;
}
