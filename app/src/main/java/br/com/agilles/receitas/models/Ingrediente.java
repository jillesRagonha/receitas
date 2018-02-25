package br.com.agilles.receitas.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by jille on 20/02/2018.
 */

public class Ingrediente implements Serializable {

    @JsonProperty("ingredient")
    private String nomeIngrediente;
    @JsonProperty("measure")
    private String medida;
    @JsonProperty("quantity")
    private float qtde;



    public Ingrediente() {
    }


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

    public float getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }



}
