package com.algaworks.ecommerce.iniciandojpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

public class ConsultandoRegistrosTest extends EntityManagerTest {

  @Test
  public void buscarPorIdentificador() {
    Produto produto = entityManager.find(Produto.class, 1);

    Assert.assertNotNull(produto);
    Assert.assertEquals("Kindle", produto.getNome());
  }

  @Test
  public void atualizarAReferencia() {
    Produto produto = entityManager.find(Produto.class, 1);
    produto.setNome("Microfone Samson");//não atualiza o banco

    entityManager.refresh(produto);//recupera a entidade do banco que não foi alterada

    Assert.assertEquals("Kindle", produto.getNome());
  }
}
