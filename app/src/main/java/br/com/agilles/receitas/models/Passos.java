package br.com.agilles.receitas.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by jille on 20/02/2018.
 */

public class Passos {

    private int id;
    @JsonProperty("shortDescription")
    private String descricaoCurta;

    @JsonProperty("description")
    private String descricao;

    @JsonProperty("videoURL")
    private String videoUrl;

    @JsonProperty("thumbnailURL")
    private String thumbnailUrl;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricaoCurta() {
        return descricaoCurta;
    }

    public void setDescricaoCurta(String descricaoCurta) {
        this.descricaoCurta = descricaoCurta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
