package br.com.agilles.receitas.delegate;

import java.util.List;

import br.com.agilles.receitas.models.Receita;

/**
 * Created by jille on 20/02/2018.
 */

public interface ReceitasDelegate {

    void lidaComReceitas(List<Receita> receitas);

    void lidaComReceitaSelecionada(Receita receita);

    void lidaComIngredienteSelecionado(Receita receita);

    void lidaComPassosReceitaSelecionada(Receita receita);

    void lidaComVideoDoPasso(String video);
}
