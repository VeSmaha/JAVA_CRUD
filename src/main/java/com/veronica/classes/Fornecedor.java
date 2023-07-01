package com.veronica.classes;
import java.util.List;

import javax.persistence.*;

@Entity
public class 


























Fornecedor {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
protected Long idFornecedor;

protected String CNPJ;

protected String Nome;

public Fornecedor(String nome, String cNPJ) {
    Nome = nome;
    CNPJ = cNPJ;
}


@OneToMany(mappedBy = "fornecedor", fetch = FetchType.EAGER)
private List<Produto> produtos;

public List<Produto> getProdutos() {
    return produtos;
}

public void setProdutos(List<Produto> produtos) {
    this.produtos = produtos;
}

// Construtor vazio necessário para o JPA
public Fornecedor(){

}

public String getNome() {
    return Nome;
}

public void setNome(String nome) {
    Nome = nome;
}





public String getCNPJ() {
    return CNPJ;
}

public void setCNPJ(String cNPJ) {
    CNPJ = cNPJ;
}

@Override
public String toString() {
    return "----Fornecedor---- \nId="+idFornecedor+"\n Nome=" + Nome + "\n CNPJ=" + CNPJ;
}

public Long getIdFornecedor() {
    return idFornecedor;
}

public void setIdFornecedor(Long idFornecedor) {
    this.idFornecedor = idFornecedor;
}





}
