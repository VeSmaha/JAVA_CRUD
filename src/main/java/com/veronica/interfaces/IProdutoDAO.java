package com.veronica.interfaces;

import java.util.List;

import com.veronica.classes.Produto;

public interface IProdutoDAO {
    public void salvarProduto(Produto produto);

    public Produto buscarProdutoPorId(Long idPr4oduto);
   
    public List<Produto> buscarTodosProdutos();
   
    public void atualizaProduto(Produto produto);
   
    public void excluirProduto(Produto produto);

}
