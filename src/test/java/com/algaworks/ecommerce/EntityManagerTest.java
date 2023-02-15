package com.algaworks.ecommerce;

import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerTest {

  protected static EntityManagerFactory entityManagerFactory;

  protected EntityManager entityManager;

  @Before
  public void setUp() {
    entityManager = entityManagerFactory.createEntityManager();
  }

  @After
  public void tearDown() {
    entityManager.close();
  }
}
