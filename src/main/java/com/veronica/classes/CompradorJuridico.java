package com.veronica.classes;

import javax.persistence.*;
@Entity
@DiscriminatorValue(value = "juridico")
public class CompradorJuridico extends Comprador{
    private String CNPJ;

    public CompradorJuridico() {
    }

    public CompradorJuridico(String nome, String cNPJ) {
        super(nome);
        CNPJ = cNPJ;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String cNPJ) {
        CNPJ = cNPJ;
    }

    @Override
    public String toString() {
        return "----Comprador Juridico---- \nNome= "+nome+"\n CNPJ=" + CNPJ;
    }

    
}
