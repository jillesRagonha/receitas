package br.com.agilles.receitas.ui.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import br.com.agilles.receitas.R;
import br.com.agilles.receitas.adapter.AdapterPassos;
import br.com.agilles.receitas.models.Receita;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PassosFragment extends Fragment {

    @BindView(R.id.rv_passos)
    RecyclerView recyclerView;
    Receita receita;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_passos, container, false);
        ButterKnife.bind(this, view);


        Bundle parametros = getArguments();

        if (parametros != null){
            receita = (Receita) parametros.getSerializable("receita");
            AdapterPassos adapterPassos = new AdapterPassos(receita.getPassos(), getContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapterPassos);

        }
        return view;

    }

    public void populaCamposComReceita(Receita receita) {

        AdapterPassos adapterPassos = new AdapterPassos(receita.getPassos(), getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterPassos);

    }


}
