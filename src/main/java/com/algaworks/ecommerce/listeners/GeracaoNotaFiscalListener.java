package com.algaworks.ecommerce.listeners;

import com.algaworks.ecommerce.model.Pedido;
import com.algaworks.ecommerce.service.NotaFiscalService;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class GeracaoNotaFiscalListener {

  private NotaFiscalService notaFiscalService = new NotaFiscalService();

  @PrePersist
  @PreUpdate
  public void gerarNota(Pedido pedido) {
    if (pedido.isPago() && pedido.getNotaFiscal() == null) {
      notaFiscalService.gerarNota(pedido);
    }
  }
}
