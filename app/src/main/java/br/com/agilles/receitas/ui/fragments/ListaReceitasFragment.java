package br.com.agilles.receitas.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.agilles.receitas.R;
import br.com.agilles.receitas.adapter.ReceitasAdapter;
import br.com.agilles.receitas.models.Receita;
import br.com.agilles.receitas.services.WebCliente;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaReceitasFragment extends Fragment {

    List<Receita> listaReceitas = new ArrayList<>();

    @BindView(R.id.lista_receitas)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_receitas, container, false);
        ButterKnife.bind(this, view);
        configuraLista();

        return view;
    }

    private void configuraLista() {

        if (!getActivity().getResources().getBoolean(R.bool.modoPaisagem)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            //TODO implementar metodo GridLayout para paisagem
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new ReceitasAdapter(listaReceitas, getContext()));

    }

    public void populaListaCom(final List<Receita> receitas) {
        this.listaReceitas = receitas;
        if (recyclerView != null) {
            configuraLista();
        }
    }

}
