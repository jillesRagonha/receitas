package br.com.agilles.receitas.services;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.com.agilles.receitas.eventBus.ReceitaEvent;
import br.com.agilles.receitas.models.Receita;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by jille on 20/02/2018.
 */

public class WebCliente {

    private static final String TAG = WebCliente.class.getSimpleName();

    private static final String SERVER_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";

    List<Receita> listaReceitas;


    private void getReceitas() {
        Retrofit cliente = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        ReceitasService service = cliente.create(ReceitasService.class);

        Call<List<Receita>> call = service.pegaListaReceitas();
        call.enqueue(new Callback<List<Receita>>() {
            @Override
            public void onResponse(Call<List<Receita>> call, Response<List<Receita>> response) {
                List<Receita> receitas = response.body();
                listaReceitas = receitas;
                EventBus.getDefault().post(new ReceitaEvent(receitas));
            }

            @Override
            public void onFailure(Call<List<Receita>> call, Throwable t) {
                Log.e("WebClient", "Falha ao carregar receitas", t);
            }
        });
    }

    public void pegarReceitas() {
        if (listaReceitas == null || listaReceitas.isEmpty()) {
            Log.d(TAG, "Carregando receitas do serviço");
            getReceitas();
        } else {
            Log.d(TAG, "Carregando receitas do banco");
            EventBus.getDefault().post(new ReceitaEvent(listaReceitas));
        }

    }

}
