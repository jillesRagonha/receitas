package br.com.agilles.receitas.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.agilles.receitas.R;
import br.com.agilles.receitas.models.Ingrediente;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jille on 21/02/2018.
 */

public class AdapterIngredientes extends BaseAdapter {
    @BindView(R.id.ingrediente_nomeIngrediente)
    TextView nomeIngrediente;

    @BindView(R.id.ingrediente_quantidade)
    TextView quantidadeIngrediente;

    @BindView(R.id.ingrediente_medida)
    TextView medidaIngrediente;
    private List<Ingrediente> listaIngredientes;
    private Activity activity;

    public AdapterIngredientes(List<Ingrediente> listaIngredientes, Activity activity) {
        this.listaIngredientes = listaIngredientes;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listaIngredientes.size();
    }

    @Override
    public Object getItem(int i) {
        return listaIngredientes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View novaView = activity.getLayoutInflater()
                .inflate(R.layout.ingrediente_list_item, viewGroup, false);
        ButterKnife.bind(this, novaView);
        Ingrediente ingrediente = listaIngredientes.get(i);
        nomeIngrediente.setText(ingrediente.getNomeIngrediente());
        medidaIngrediente.setText(ingrediente.getMedida());
        quantidadeIngrediente.setText(String.valueOf(ingrediente.getQtde()));
        return novaView;

    }


}
