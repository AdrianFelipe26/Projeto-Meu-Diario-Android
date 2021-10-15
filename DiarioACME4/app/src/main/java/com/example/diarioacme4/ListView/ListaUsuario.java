package com.example.diarioacme4.ListView;

import java.io.Serializable;

public class ListaUsuario implements Serializable {

    private Long ID;
    public String nome;
    @Override
    public String toString(){
        return  "Por: " + nome.toString() + "\n";

    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}



