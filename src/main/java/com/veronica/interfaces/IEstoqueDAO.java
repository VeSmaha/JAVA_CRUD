package com.veronica.interfaces;

import java.util.List;

import com.veronica.classes.Estoque;

public interface IEstoqueDAO {
    public void salvarEstoque(Estoque estoque);

    public Estoque buscarEstoquePorId(Long idEstoque);
   
    public List<Estoque> buscarTodosEstoques();
   
    public void atualizarestoque(Estoque estoque);
   
    public void excluirEstoque(Estoque estoque);

}
