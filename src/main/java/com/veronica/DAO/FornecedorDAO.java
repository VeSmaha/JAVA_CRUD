package com.veronica.DAO;
import javax.persistence.EntityManager;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.Persistence;
 import javax.persistence.Query;

import com.veronica.classes.Fornecedor;
import com.veronica.interfaces.IFornecedorDAO;

import java.util.List;

public class FornecedorDAO implements IFornecedorDAO{
    
    private EntityManagerFactory emf;

    public FornecedorDAO() {
        emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }
   
    public void salvarFornecedor(Fornecedor fornecedor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(fornecedor);
        em.getTransaction().commit();
        em.close();
    }
   
    public Fornecedor buscarFornecedorPorId(Long IdFornecedor) {
        EntityManager em = emf.createEntityManager();
        Fornecedor fornecedor = em.find(Fornecedor.class, IdFornecedor);
        em.close();
        return fornecedor;
    }
    public List<Fornecedor> buscarTodosFornecedores() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT f FROM Fornecedor f");
        List<Fornecedor> fornecedores = query.getResultList();
        em.close();
        return fornecedores;
    }
   
    public void atualizarFornecedor(Fornecedor fornecedor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(fornecedor);
        em.getTransaction().commit();
        em.close();
    }
   
   public void excluirFornecedor(Fornecedor fornecedor) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    // Exclui os produtos associados ao fornecedor
    Query deleteProdutosQuery = em.createQuery("DELETE FROM Produto p WHERE p.fornecedor = :fornecedor");
    deleteProdutosQuery.setParameter("fornecedor", fornecedor);
    deleteProdutosQuery.executeUpdate();

    // Remove o fornecedor
    fornecedor = em.merge(fornecedor);
    em.remove(fornecedor);
    
    em.getTransaction().commit();
    em.close();
}

   }
