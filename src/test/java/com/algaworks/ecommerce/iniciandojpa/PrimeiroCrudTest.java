package com.algaworks.ecommerce.iniciandojpa;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

  @Test
  public void createCliente() {
    Cliente cliente = new Cliente();

    cliente.setNome("João Carlos");

    entityManager.getTransaction().begin();
    entityManager.persist(cliente);
    entityManager.getTransaction().commit();

    Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
    Assert.assertNotNull(clienteVerificacao);
  }

  @Test
  public void readCliente() {
    Cliente cliente = entityManager.find(Cliente.class, 1);

    Assert.assertEquals("Fernando Medeiros", cliente.getNome());
  }

  @Test
  public void updateCliente() {
    Cliente cliente = new Cliente();
    cliente.setNome("João das couves");

    entityManager.getTransaction().begin();
    cliente = entityManager.merge(cliente);
    entityManager.getTransaction().commit();

    entityManager.clear();

    Cliente clienteBanco = entityManager.find(Cliente.class, cliente.getId());
    Assert.assertEquals("João das couves", clienteBanco.getNome());


  }

  @Test
  public void removeCliente() {
    Cliente cliente = entityManager.find(Cliente.class, 2);

    entityManager.getTransaction().begin();
    entityManager.remove(cliente);
    entityManager.getTransaction().commit();

    Cliente clienteBanco = entityManager.find(Cliente.class, 2);
    Assert.assertNull(clienteBanco);
  }
}
