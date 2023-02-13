package com.algaworks.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@DiscriminatorValue("cartao")
//@Table(name = "pagamento_cartao")
public class PagamentoCartao extends Pagamento {

    @NotBlank
    @Column(name = "numero_cartao", length = 50)
    private String numeroCartao;
}
