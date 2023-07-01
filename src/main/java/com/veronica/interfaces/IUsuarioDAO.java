package com.veronica.interfaces;

import java.util.List;

import com.veronica.classes.Usuario;

public interface IUsuarioDAO {
   
    public void salvarUsuario(Usuario usuario);
   
    public List<Usuario> buscarTodosUsuarios();
   
    public void atualizarUsuario(Usuario usuario);
   
    public void excluirUsuario(Usuario usuario);
   }