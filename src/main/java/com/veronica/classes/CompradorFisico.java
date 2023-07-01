package com.veronica.classes;

import javax.persistence.*;
@Entity
@DiscriminatorValue(value = "fisico")
public class CompradorFisico extends Comprador{
    private String CPF;

    
    public CompradorFisico() {
    }

    public CompradorFisico(String nome, String cPF) {
        super(nome);
        CPF = cPF;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    @Override
    public String toString() {
        return "----Comprador Fisico----\nNome = "+nome+"\n CPF=" + CPF;
    }
    
    
    
}
