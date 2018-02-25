package br.com.agilles.receitas.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.agilles.receitas.R;
import br.com.agilles.receitas.adapter.AdapterIngredientes;
import br.com.agilles.receitas.models.Receita;
import butterknife.BindView;
import butterknife.ButterKnife;


public class IngredientesFragment extends Fragment {

    @BindView(R.id.ingredientes_list_view)
    RecyclerView listaIngredientes;

    Receita receita = new Receita();

    @BindView(R.id.textViewReceitaVazia)
    TextView mTextReceitasVazia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredientes, container, false);
        ButterKnife.bind(this, view);

        Bundle parametros = getArguments();


        if (parametros != null) {
            receita = (Receita) parametros.getSerializable("receita");

            AdapterIngredientes adapter = new AdapterIngredientes(receita.getIngredientes(), getContext());

            listaIngredientes.setLayoutManager(new LinearLayoutManager(getContext()));
            listaIngredientes.setHasFixedSize(true);
            listaIngredientes.setAdapter(adapter);
            mTextReceitasVazia.setVisibility(View.GONE);
        }


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            this.receita = (Receita) savedInstanceState.getSerializable("receita");
        }
    }

    public void populaCamposComReceita(Receita receita) {
        AdapterIngredientes adapterIngredientes = new AdapterIngredientes(receita.getIngredientes(), getActivity());
        listaIngredientes.setLayoutManager(new LinearLayoutManager(getContext()));
        listaIngredientes.setHasFixedSize(true);
        listaIngredientes.setAdapter(adapterIngredientes);
        mTextReceitasVazia.setVisibility(View.GONE);


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("receita", receita);
    }
}
