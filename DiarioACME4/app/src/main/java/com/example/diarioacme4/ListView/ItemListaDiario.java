package com.example.diarioacme4.ListView;

import java.io.Serializable;

public class ItemListaDiario implements Serializable {
    private Long ID;
    public String Titulo;
    public String Conteudo;
    public String PalavraSecreta;
    @Override
    public String toString(){


        return  "Titulo: " + Titulo.toString() + "\n";

    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getConteudo() {
        return Conteudo;
    }

    public void setConteudo(String conteudo) {
        Conteudo = conteudo;
    }

    public String getPalavraSecreta() {
        return PalavraSecreta;
    }

    public void setPalavraSecreta(String palavraSecreta) {
        PalavraSecreta = palavraSecreta;
    }
}

