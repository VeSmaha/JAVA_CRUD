package com.veronica.classes;
import java.util.List;

import javax.persistence.*;

@Entity

//-@Entity: Essa anotação é usada para indicar que a classe Pessoa é uma entidade que será
//mapeada para uma tabela no banco de dados. É uma anotação obrigatória para todas as entidades
//JPA
public class Produto {
   
    //@Id: Essa anotação é usada para indicar que o campo id é a chave primária da entidade Pessoa. A
//anotação @GeneratedValue é usada em conjunto com @Id para especificar a estratégia de geração
//automática de valores para a chave primária. No nosso caso, a estratégia utilizada é
//GenerationType.IDENTITY, que indica que o valor do ID será gerado automaticamente pelo
//banco de dados.

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 protected Long idProduto;

 protected Double preco;

 protected String nome;

 public Produto(Double p, String nome) {
 this.preco = p;
 this.nome = nome;
 }

 public Produto(){

 }

 @OneToOne(mappedBy = "produto", cascade = CascadeType.ALL)
 protected Estoque estoque;
 public Estoque getEstoque() {
    return estoque;
}
public int getqtdEstoque() {
    return estoque.getQuantidade();
}


public void setQtdEstoque(int qtd) {
    estoque.quantidade = qtd;
}
public void setEstoque(Estoque estoque) {
    this.estoque = estoque;
}


@ManyToOne
@JoinColumn(name = "idFornecedor")
protected Fornecedor fornecedor;


public Fornecedor getFornecedor() {
    return fornecedor;
}

public void setFornecedor(Fornecedor fornecedor) {
    this.fornecedor = fornecedor;
}
@ManyToMany(mappedBy = "produtos")
private List<Compra> vendas;

public List<Compra> getVendas() {
    return vendas;
}

public void setVendas(List<Compra> vendas) {
    this.vendas = vendas;
}


public Long getIdProduto() {
    return idProduto;
}



public void setIdProduto(Long idProduto) {
    this.idProduto = idProduto;
}



public Double getPreco() {
    return preco;
}



@Override
public String toString() {
    return "----Produto---- \nidProduto=" + idProduto + " \npreco=" + preco + "\n" + //
            "qtd em estoque="+estoque.getQuantidade()+" \nnome=" + nome+" \nCNPJ do Fornecedor=" + (fornecedor != null ? fornecedor.getCNPJ() : null);
}

public void setPreco(Double preco) {
    this.preco = preco;
}

public String getNome() {
 return nome;
 }

 public void setNome(String nome) {
 this.nome = nome;
 }
}
