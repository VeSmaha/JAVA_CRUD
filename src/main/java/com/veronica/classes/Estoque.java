package com.veronica.classes;
import javax.persistence.*;
@Entity

//-@Entity: Essa anotação é usada para indicar que a classe Pessoa é uma entidade que será
//mapeada para uma tabela no banco de dados. É uma anotação obrigatória para todas as entidades
//JPA
public class Estoque {
    
  
    //@Id: Essa anotação é usada para indicar que o campo id é a chave primária da entidade Pessoa. A
//anotação @GeneratedValue é usada em conjunto com @Id para especificar a estratégia de geração
//automática de valores para a chave primária. No nosso caso, a estratégia utilizada é
//GenerationType.IDENTITY, que indica que o valor do ID será gerado automaticamente pelo
//banco de dados.

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
protected Long idEstoque;

protected int quantidade;

//Relacionamento com Produto
@OneToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "idProduto")
protected Produto produto;
//Construtor vazio
public Estoque(){

}
public Estoque(int quantidade, Produto produto) {
    this.quantidade = quantidade;
    this.produto = produto;
}
public Produto getProduto(){
    return produto;
}
public void setProduto(Produto produto){
    this.produto = produto;
}

@Override
public String toString() {
    return "----Estoque---- \nidEstoque=" + idEstoque + "\n quantidade=" + quantidade + "\n produto=" + (produto != null ? produto.getNome() : null);
}
public Long getIdEstoque() {
    return idEstoque;
}
public void setIdEstoque(Long idEstoque) {
    this.idEstoque = idEstoque;
}

public int getQuantidade() {
    return quantidade;
}

public String getName() {
    return produto.getNome();
}
public void setQuantidade(int quantidade) {
    this.quantidade = quantidade;
}





}

