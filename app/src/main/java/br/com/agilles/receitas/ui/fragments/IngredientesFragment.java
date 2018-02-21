package br.com.agilles.receitas.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.agilles.receitas.R;
import br.com.agilles.receitas.adapter.AdapterIngredientes;
import br.com.agilles.receitas.models.Ingrediente;
import br.com.agilles.receitas.models.Receita;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class IngredientesFragment extends Fragment {



    @BindView(R.id.ingredientes_list_view)
    ListView listaIngredientes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredientes, container, false);
        ButterKnife.bind(this, view);

        Bundle parametros = getArguments();


        if (parametros != null) {
            Receita receita = (Receita) parametros.getSerializable("receita");
            Ingrediente ingrediente = new Ingrediente();
            AdapterIngredientes adapter = new AdapterIngredientes(receita.getIngredientes(), getActivity());
            listaIngredientes.setAdapter(adapter);

        }



        return view;
    }






}
