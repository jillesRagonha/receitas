package br.com.agilles.receitas.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by jille on 20/02/2018.
 */

@JsonIgnoreProperties(ignoreUnknown = true)

public class Receita {

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
}
