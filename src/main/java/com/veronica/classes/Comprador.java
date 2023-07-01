package com.veronica.classes;
import java.util.List;
import javax.persistence.*;
@Entity

public class Comprador{
 
   
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
protected Long idComprador;
protected String nome;

@ManyToMany(mappedBy = "comprador")
private List<Compra> vendas;

public List<Compra> getVendas() {
    return vendas;
}

public void setVendas(List<Compra> vendas) {
    this.vendas = vendas;
}

public Comprador() {
}


public Long getIdComprador() {
    return idComprador;
}

public void setIdComprador(Long idComprador) {
    this.idComprador = idComprador;
}

@Override
public String toString() {
    return "----Comprador---- \nidComprador=" + idComprador + "\n compras=" + vendas;
}

public Comprador(String nome) {
    this.nome = nome;
}

public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}






}
