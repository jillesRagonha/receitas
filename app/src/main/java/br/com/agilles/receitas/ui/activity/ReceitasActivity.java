package br.com.agilles.receitas.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import br.com.agilles.receitas.R;
import br.com.agilles.receitas.delegate.ReceitasDelegate;
import br.com.agilles.receitas.eventBus.ReceitaEvent;
import br.com.agilles.receitas.models.Receita;
import br.com.agilles.receitas.services.WebCliente;
import br.com.agilles.receitas.ui.fragments.ListaReceitasFragment;
import butterknife.ButterKnife;

public class ReceitasActivity extends AppCompatActivity implements ReceitasDelegate {

    WebCliente webCliente = new WebCliente();

    ListaReceitasFragment listaReceitasFragment = new ListaReceitasFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);

        ButterKnife.bind(this);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = supportFragmentManager.beginTransaction();
        tx.replace(R.id.frame_principal, listaReceitasFragment);

        if (estaNoModoPaisagem()) {
            //TODO criar o fragment do modo paisagem
        }

        tx.commit();
    }

    private boolean estaNoModoPaisagem() {
        return getResources().getBoolean(R.bool.modoPaisagem);
    }

    @Override
    public void lidaComReceitas(List<Receita> receitas) {

    }

    @Override
    public void lidaComReceitaSelecionada(Receita receita) {

    }

    @Subscribe
    public void lidaComSucesso(ReceitaEvent event) {
        listaReceitasFragment.populaListaCom(event.listaReceitas);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        webCliente.pegarReceitas();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}
