package br.com.agilles.receitas.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.agilles.receitas.R;
import br.com.agilles.receitas.models.Ingrediente;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jille on 21/02/2018.
 */

public class AdapterIngredientes extends RecyclerView.Adapter<AdapterIngredientes.IngredientesViewHolder> {


    private List<Ingrediente> listaIngredientes;
    private Context context;

    public AdapterIngredientes(List<Ingrediente> listaIngredientes, Context context) {
        this.listaIngredientes = listaIngredientes;
        this.context = context;
    }

    @Override
    public IngredientesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int idDoLayout = R.layout.ingrediente_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(idDoLayout, parent, false);
        return new IngredientesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(IngredientesViewHolder holder, int position) {
        IngredientesViewHolder ingredientesViewHolder = (IngredientesViewHolder) holder;
        Ingrediente ingrediente = listaIngredientes.get(position);
        ingredientesViewHolder.bind(ingrediente);

    }

    @Override
    public int getItemCount() {
        return listaIngredientes.size();
    }


    public class IngredientesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ingrediente_nomeIngrediente)
        TextView nomeIngrediente;

        @BindView(R.id.ingrediente_quantidade)
        TextView quantidadeIngrediente;

        @BindView(R.id.ingrediente_medida)
        TextView medidaIngrediente;

        public IngredientesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        void bind(Ingrediente ingrediente) {
            nomeIngrediente.setText(ingrediente.getNomeIngrediente());
            medidaIngrediente.setText(ingrediente.getMedida());
            quantidadeIngrediente.setText(String.valueOf(ingrediente.getQtde()));

        }
    }

}
