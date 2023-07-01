package com.veronica.DAO;
import javax.persistence.EntityManager;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.Persistence;
 import javax.persistence.Query;


import com.veronica.classes.Compra;
import com.veronica.interfaces.ICompraDAO;

import java.util.List;

public class CompraDAO implements ICompraDAO{
    
    private EntityManagerFactory emf;
  

    public CompraDAO() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }
   
    public void salvarCompra(Compra compra) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(compra);
        em.getTransaction().commit();
        em.close();
    }
   
    public Compra buscarCompraPorId(Long idCompra) {
        EntityManager em = emf.createEntityManager();
        Compra compra = em.find(Compra.class, idCompra);
        em.close();
        return compra;
    }
    public List<Compra> buscarTodasCompras() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Compra c");
        List<Compra> compras = query.getResultList();
        em.close();
        return compras;
    }
 

   
   
    public void excluirCompra(Compra compra) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        compra = em.merge(compra);
        em.remove(compra);
        em.getTransaction().commit();
        em.close();
    }

   }
