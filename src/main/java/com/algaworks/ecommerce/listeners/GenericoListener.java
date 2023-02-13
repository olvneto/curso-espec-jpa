package com.algaworks.ecommerce.listeners;

import javax.persistence.PostLoad;

public class GenericoListener {

    @PostLoad
    public void logCarregamento(Object obj) {
        System.out.println("Entidade " + obj.getClass().getSimpleName() + " foi carregada.");
    }
}
