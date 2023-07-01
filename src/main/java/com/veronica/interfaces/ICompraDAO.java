package com.veronica.interfaces;

import java.util.List;

import com.veronica.classes.Compra;

public interface ICompraDAO {
  
    public void salvarCompra(Compra compra) ;
    public Compra buscarCompraPorId(Long idCompra);
    public List<Compra> buscarTodasCompras();
    public void excluirCompra(Compra compra);

   }