package com.algaworks.ecommerce.iniciandojpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class OperacoesComTransacao extends EntityManagerTest {

  @Test
  public void impedirOperacaoComBancoDeDados() {
    Produto produto = entityManager.find(Produto.class, 1);
    entityManager.detach(produto);

    entityManager.getTransaction().begin();
    produto.setNome("Kindle Paperwhite 2");//não precisa do merge
    entityManager.getTransaction().commit();

    entityManager.clear();//apagando o objeto produto da memória

    Produto prodBanco = entityManager.find(Produto.class, produto.getId());//trouxe o produto do banco
    Assert.assertEquals("Kindle", prodBanco.getNome());
  }

  @Test
  public void inserirProdutoComMerge() {
    Produto produto = new Produto();
    produto.setNome("Microfone Rode Videmic");
    produto.setDescricao("A melhor solução para som.");
    produto.setPreco(new BigDecimal(1_000));

    entityManager.getTransaction().begin();
    produto = entityManager.merge(produto);
    entityManager.getTransaction().commit();

    entityManager.clear();//apagando o objeto produto da memória

    Produto prodBanco = entityManager.find(Produto.class, produto.getId());//trouxe o produto do banco
    Assert.assertNotNull(prodBanco);

  }

  @Test
  public void atualizaProdutoGerenciado() {
    Produto produto = entityManager.find(Produto.class, 1);

    entityManager.getTransaction().begin();
    produto.setNome("Kindle PaperWhite");//não precisa do merge
    entityManager.getTransaction().commit();

    entityManager.clear();//apagando o objeto produto da memória

    Produto prodBanco = entityManager.find(Produto.class, produto.getId());//trouxe o produto do banco
    Assert.assertEquals("Kindle PaperWhite", prodBanco.getNome());
  }


  @Test
  public void atualizaProduto() {
    Produto produto = new Produto();
    produto.setNome("Kindle PaperWhite");
    produto.setDescricao("O novo Kindle");
    produto.setPreco(new BigDecimal(529));

    entityManager.getTransaction().begin();
    produto = entityManager.merge(produto);
    entityManager.getTransaction().commit();

    entityManager.clear();//apagando o objeto produto da memória

    Produto prodBanco = entityManager.find(Produto.class, produto.getId());//trouxe o produto do banco
    Assert.assertNotNull(prodBanco);
    Assert.assertEquals("Kindle PaperWhite", prodBanco.getNome());
  }

  @Test
  public void removeProduto() {
    Produto produto = entityManager.find(Produto.class, 3);// o objeto precisa ir para a memória para ser
    //removido

    entityManager.getTransaction().begin();
    entityManager.remove(produto);
    entityManager.getTransaction().commit();

    Produto produtoVerificacao = entityManager.find(Produto.class, 3);
    Assert.assertNull(produtoVerificacao);
  }

  @Test
  public void inserirProduto() {
    Produto produto = new Produto();
    produto.setNome("Câmera Canon");
    produto.setDescricao("A melhor definição para as suas fotos");
    produto.setPreco(new BigDecimal(5_000));

    entityManager.getTransaction().begin();
    entityManager.persist(produto);
    entityManager.getTransaction().commit();

    entityManager.clear();//apagando o objeto produto da memória

    Produto prodBanco = entityManager.find(Produto.class, produto.getId());//trouxe o produto do banco
    Assert.assertNotNull(prodBanco);

  }

  @Test
  public void abrirEFecharTransacao() {
//        Produto produto = new Produto();

    entityManager.getTransaction().begin();

//        entityManager.persist(produto);
//        entityManager.merge(produto);
//        entityManager.remove(produto);

    entityManager.getTransaction().commit();
  }
}
