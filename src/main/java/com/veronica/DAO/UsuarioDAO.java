package com.veronica.DAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.veronica.classes.Usuario;
import com.veronica.interfaces.IUsuarioDAO;

import java.util.List;

public class UsuarioDAO implements IUsuarioDAO{
    private EntityManagerFactory emf;

    public UsuarioDAO() {
    emf = Persistence.createEntityManagerFactory("my-persistence-unit");
    }
   
    public void salvarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }
   
    public List<Usuario> buscarTodosUsuarios() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT u FROM Usuario u");
        List<Usuario> usuarios = query.getResultList();
        em.close();
        return usuarios;
    }
   
    public void atualizarUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
        em.close();
    }
   
    public void excluirUsuario(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        usuario = em.merge(usuario);
        em.remove(usuario);
        em.getTransaction().commit();
        em.close();
    }
   }
