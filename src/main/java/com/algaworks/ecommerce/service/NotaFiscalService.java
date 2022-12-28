package com.algaworks.ecommerce.service;

import com.algaworks.ecommerce.model.Pedido;

public class NotaFiscalService {

  public void gerarNota(Pedido pedido) {
    System.out.printf("Gerando nota fiscal do pedido: %d\n", pedido.getId());
  }
}
