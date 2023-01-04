package com.algaworks.ecommerce.model;

import com.algaworks.ecommerce.listeners.GeracaoNotaFiscalListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners({GeracaoNotaFiscalListener.class})
@Table(name = "pedido")
public class Pedido extends EntidadeBaseInteger {

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;

    @Column(name = "data_criacao", updatable = false, nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_ultima_atualizacao", insertable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_conclusao")
    private LocalDateTime dataConclusao;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Embedded
    private EnderecoEntregaPedido enderecoEntrega;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;

    @OneToOne(mappedBy = "pedido")
    private Pagamento pagamento;

    public boolean isPago() {
        return status.equals(StatusPedido.PAGO);
    }

    public void calcularTotal() {
        if (itens != null) {
            total = itens.stream().map(ItemPedido::getPrecoProduto)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    @PrePersist //só pode haver um método com esta anotação na classe
    public void aoPersistir() {
        dataCriacao = LocalDateTime.now();
        this.calcularTotal();
    }

    @PreUpdate //só pode haver um método com esta anotação na classe
    public void aoAtualizar() {
        dataUltimaAtualizacao = LocalDateTime.now();
        this.calcularTotal();
    }
}
