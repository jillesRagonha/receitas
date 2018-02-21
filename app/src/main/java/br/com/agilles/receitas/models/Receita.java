package br.com.agilles.receitas.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jille on 20/02/2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class Receita implements Serializable {

    @JsonProperty("image")
    private String imagem;

    @JsonProperty("name")
    private String nome;

    @JsonProperty("servings")
    private int rendimento;

    @JsonProperty("ingredients")
    private List<Ingrediente> ingredientes;

    @JsonProperty("steps")
    private List<Passos> passos;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public List<Passos> getPassos() {
        return passos;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setPassos(List<Passos> passos) {
        this.passos = passos;
    }

    public int getRendimento() {
        return rendimento;
    }

    public void setRendimento(int rendimento) {
        this.rendimento = rendimento;
    }


}
