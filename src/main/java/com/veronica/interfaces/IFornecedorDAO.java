package com.veronica.interfaces;

import java.util.List;

import com.veronica.classes.Fornecedor;

public interface IFornecedorDAO {
    public void salvarFornecedor(Fornecedor fornecedor) ;
    public Fornecedor buscarFornecedorPorId(Long IdFornecedor);
    public List<Fornecedor> buscarTodosFornecedores();
    public void atualizarFornecedor(Fornecedor fornecedor);
    public void excluirFornecedor(Fornecedor fornecedor);

   }

