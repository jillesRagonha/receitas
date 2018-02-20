package br.com.agilles.receitas.services;

import java.util.List;

import br.com.agilles.receitas.models.Receita;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jille on 20/02/2018.
 */

public interface ReceitasService {
    @GET("baking.json")
    Call<List<Receita>> pegaListaReceitas();
}
