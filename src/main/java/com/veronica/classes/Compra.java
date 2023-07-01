package com.veronica.classes;
import java.util.List;

import javax.persistence.*;
import javax.transaction.Transactional;
@Entity

public class Compra {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
protected Long idCompra;
protected Double precoTotal;
//Relacionamento com Produto
@OneToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "idComprador")
protected Comprador comprador;


@ManyToMany
@JoinTable(
    name = "venda_produto",
    joinColumns = @JoinColumn(name = "compra_id"),
    inverseJoinColumns = @JoinColumn(name = "produto_id")
)
protected List<Produto> produtos;




public Compra(Double precoTotal, Comprador comprador, List<Produto> produtos) {
    this.precoTotal = precoTotal;
    this.comprador = comprador;
    this.produtos = produtos;
}


public Compra() {
   
}
public Double getPrecoTotal() {
    return precoTotal;
}
public void setPrecoTotal(Double precoTotal) {
    this.precoTotal = precoTotal;
}

public List<Produto> getProdutos() {
    return produtos;
}
public void setProdutos(List<Produto> produtos) {
    this.produtos = produtos;
}
public Comprador getComprador() {
    return comprador;
}
public void setComprador(Comprador comprador) {
    this.comprador = comprador;
}

public Long getIdCompra() {
    return idCompra;
}

public void setIdCompra(Long idCompra) {
    this.idCompra = idCompra;
}

@Transactional
@Override
public String toString() {
    // Carrega a lista de produtos antes de convertê-la em string
    if (produtos != null) {
        produtos.size(); // Carrega a lista de produtos
    }
    
    return "----Compra----\nidCompra=" + idCompra + "\n precoTotal=" + precoTotal + "\n comprador=" + comprador;
}



}
