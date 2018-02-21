package br.com.agilles.receitas.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.agilles.receitas.R;
import br.com.agilles.receitas.delegate.ReceitasDelegate;
import br.com.agilles.receitas.models.Receita;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jille on 20/02/2018.
 */

public class ReceitasAdapter extends RecyclerView.Adapter<ReceitasAdapter.ReceitasViewHolder> {

    private List<Receita> listaReceitas;
    private final Context context;

    public ReceitasAdapter(List<Receita> listaReceitas, Context context) {
        this.listaReceitas = listaReceitas;
        this.context = context;
    }

    @Override
    public ReceitasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForItem = R.layout.item_lista_receitas;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForItem, parent, false);
        return new ReceitasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReceitasViewHolder holder, int position) {
        ReceitasViewHolder receitasViewHolder = (ReceitasViewHolder) holder;
        Receita receita = listaReceitas.get(position);
        receitasViewHolder.bind(receita);

    }

    @Override
    public int getItemCount() {
        return listaReceitas.size();
    }

    public class ReceitasViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.receitas_imagem_principal)
        ImageView mImagemPrincipal;

        @BindView(R.id.receitas_nome_receita)
        TextView mTxtNomeReceita;


        public ReceitasViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Receita receita) {
            mTxtNomeReceita.setText(receita.getNome());
            if (receita.getImagem().isEmpty() || receita.getImagem() == null) {
                Picasso.with(mImagemPrincipal.getContext())
                        .load(R.drawable.food_placeholder)
                        .placeholder(R.drawable.food_placeholder)
                        .error(R.drawable.food_placeholder)
                        .into(mImagemPrincipal);

            } else {
                Picasso.with(mImagemPrincipal.getContext())
                        .load(receita.getImagem())
                        .placeholder(R.drawable.food_placeholder)
                        .error(R.drawable.food_placeholder)
                        .into(mImagemPrincipal);
            }

        }

        @OnClick(R.id.item_receita)
        void clickItem() {
            Receita receita = listaReceitas.get(getAdapterPosition());
            ReceitasDelegate delegate = (ReceitasDelegate) itemView.getContext();
            delegate.lidaComReceitaSelecionada(receita);
        }

    }


}

