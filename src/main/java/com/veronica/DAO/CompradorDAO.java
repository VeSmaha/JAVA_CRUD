package com.veronica.DAO;
import javax.persistence.EntityManager;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.Persistence;
 import javax.persistence.Query;

import com.veronica.classes.Comprador;
import com.veronica.interfaces.ICompradorDAO;

import java.util.List;

public class CompradorDAO implements ICompradorDAO{
    private EntityManagerFactory emf;

    public CompradorDAO() {
    emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }
   
    public void salvarComprador(Comprador comprador) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(comprador);
        em.getTransaction().commit();
        em.close();
    }
   
    public Comprador buscarCompradorPorIdP(Long idComprador) {
        EntityManager em = emf.createEntityManager();
        Comprador comprador = em.find(Comprador.class, idComprador);
        em.close();
        return comprador;
    }
   
    public List<Comprador> buscarTodosCompradores() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT d FROM Comprador d");
        List<Comprador> compradores = query.getResultList();
        em.close();
        return compradores;
    }
   
    public void atualizarComprador(Comprador comprador) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(comprador);
        em.getTransaction().commit();
        em.close();
    }
   
    public void excluirComprador(Comprador comprador) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        comprador = em.merge(comprador);
        em.remove(comprador);
        em.getTransaction().commit();
        em.close();
    }
   }
