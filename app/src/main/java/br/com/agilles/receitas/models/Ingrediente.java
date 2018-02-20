package br.com.agilles.receitas.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jille on 20/02/2018.
 */

public class Ingrediente {

    @JsonProperty("ingredient")
    private String nomeIngrediente;
    @JsonProperty("measure")
    private String medida;
    @JsonProperty("quantity")
    private int qtde;


    public String getNomeIngrediente() {
        return nomeIngrediente;
    }

    public void setNomeIngrediente(String nomeIngrediente) {
        this.nomeIngrediente = nomeIngrediente;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }
}
