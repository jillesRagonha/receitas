package br.com.agilles.receitas.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import br.com.agilles.receitas.R;
import br.com.agilles.receitas.models.Passos;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jille on 25/02/2018.
 */

public class AdapterPassos extends RecyclerView.Adapter<AdapterPassos.ViewHolder> {

    private List<Passos> listaPassos;
    private Context context;

    public AdapterPassos(List<Passos> listaPassos, Context context) {
        this.listaPassos = listaPassos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_passos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ViewHolder viewHolder = (ViewHolder) holder;
        Passos passos = listaPassos.get(position);
        viewHolder.bind(passos);

    }

    @Override
    public int getItemCount() {
        return listaPassos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_id)
        TextView mTxtId;
        @BindView(R.id.txt_descricao_curta)
        TextView mTxtDescricaoCurta;
        @BindView(R.id.txt_descricao)
        TextView getmTxtDescricao;

        @BindView(R.id.btn_camera)
        ImageButton btnCamera;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        void bind(Passos passos) {
            mTxtId.setText(String.valueOf(passos.getId()+1));
            mTxtDescricaoCurta.setText(passos.getDescricaoCurta());
            getmTxtDescricao.setText(passos.getDescricao());
            if (passos.getVideoUrl().isEmpty() || passos.getVideoUrl() == null) {
                btnCamera.setVisibility(View.GONE);
            }


        }
    }
}

