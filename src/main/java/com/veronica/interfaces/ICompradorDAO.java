package com.veronica.interfaces;

import java.util.List;


import com.veronica.classes.Comprador;

public interface ICompradorDAO {
   
    public void salvarComprador(Comprador comprador);
   
    public Comprador buscarCompradorPorIdP(Long idComprador);
   
    public List<Comprador> buscarTodosCompradores();
   
    public void atualizarComprador(Comprador comprador);
   
    public void excluirComprador(Comprador comprador);
   }
