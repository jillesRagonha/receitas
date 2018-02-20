package br.com.agilles.receitas.eventBus;

import java.util.List;

import br.com.agilles.receitas.models.Receita;

/**
 * Created by jille on 20/02/2018.
 */

public class ReceitaEvent {

    public final List<Receita> listaReceitas;

    public ReceitaEvent(List<Receita> listaReceitas) {
        this.listaReceitas = listaReceitas;
    }
}
