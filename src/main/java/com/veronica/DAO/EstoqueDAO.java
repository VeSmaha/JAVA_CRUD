package com.veronica.DAO;
import javax.persistence.EntityManager;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.Persistence;
 import javax.persistence.Query;

import com.veronica.classes.Estoque;
import com.veronica.interfaces.IEstoqueDAO;

import java.util.List;

public class EstoqueDAO implements IEstoqueDAO{
    private EntityManagerFactory emf;

    public EstoqueDAO() {
    emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }
   
    public void salvarEstoque(Estoque estoque) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.persist(estoque);
    em.getTransaction().commit();
    em.close();
    }
   
    public Estoque buscarEstoquePorIdProduto(Long idProduto) {
    EntityManager em = emf.createEntityManager();
    Estoque estoque = em.find(Estoque.class, idProduto);
    em.close();
    return estoque;
    }

    public Estoque buscarEstoquePorId(Long idEstoque) {
        EntityManager em = emf.createEntityManager();
        Estoque estoque = em.find(Estoque.class, idEstoque);
        em.close();
        return estoque;
    }
   
    public List<Estoque> buscarTodosEstoques() {
    EntityManager em = emf.createEntityManager();
    Query query = em.createQuery("SELECT e FROM Estoque e");
    List<Estoque> estoques = query.getResultList();
    em.close();
    return estoques;
    }
   
    public void atualizarestoque(Estoque estoque) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    em.merge(estoque);
    em.getTransaction().commit();
    em.close();
    }
   
    public void excluirEstoque(Estoque estoque) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    estoque = em.merge(estoque);
    em.remove(estoque);
    em.getTransaction().commit();
    em.close();
    }
   }
   
