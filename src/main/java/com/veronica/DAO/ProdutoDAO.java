package com.veronica.DAO;

import javax.persistence.EntityManager;
 import javax.persistence.EntityManagerFactory;
 import javax.persistence.Persistence;
 import javax.persistence.Query;

import com.veronica.classes.Fornecedor;
import com.veronica.classes.Produto;
import com.veronica.interfaces.*;

import java.util.List;
public class ProdutoDAO implements IProdutoDAO{
    private EntityManagerFactory emf;

 public ProdutoDAO() {
 emf = Persistence.createEntityManagerFactory("my-persistence-unit");
 }

 public void salvarProduto(Produto produto) {
 EntityManager em = emf.createEntityManager();
 em.getTransaction().begin();
 em.persist(produto);
 em.getTransaction().commit();
 em.close();
 }

 public Produto buscarProdutoPorId(Long idProduto) {
    EntityManager em = emf.createEntityManager();
    Produto produto = em.find(Produto.class, idProduto);
    em.close();
    return produto;
 }

 public List<Produto> buscarTodosProdutos() {
 EntityManager em = emf.createEntityManager();
 Query query = em.createQuery("SELECT p FROM Produto p");
 List<Produto> produtos = query.getResultList();
 em.close();
 return produtos;
 }

 public void atualizaProduto(Produto produto) {
 EntityManager em = emf.createEntityManager();
 em.getTransaction().begin();
 em.merge(produto);
 em.getTransaction().commit();
 em.close();
 }

//  public void excluirProduto(Produto produto) {
//  EntityManager em = emf.createEntityManager();
//  em.getTransaction().begin();
// produto = em.merge(produto);
// em.remove(produto);
// em.getTransaction().commit();
// em.close();
// }

public void excluirProduto(Produto produto) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();

    // Exclui o estoque associado ao produto
    Query deleteEstoqueQuery = em.createQuery("DELETE FROM Estoque e WHERE e.produto = :produto");
    deleteEstoqueQuery.setParameter("produto", produto);
    deleteEstoqueQuery.executeUpdate();

    // Remove o produto
    produto = em.merge(produto);
    em.remove(produto);
    
    em.getTransaction().commit();
    em.close();
}

}
