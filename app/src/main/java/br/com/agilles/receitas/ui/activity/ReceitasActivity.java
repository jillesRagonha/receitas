package br.com.agilles.receitas.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import br.com.agilles.receitas.R;
import br.com.agilles.receitas.delegate.ReceitasDelegate;
import br.com.agilles.receitas.eventBus.ReceitaEvent;
import br.com.agilles.receitas.models.Receita;
import br.com.agilles.receitas.services.WebCliente;
import br.com.agilles.receitas.ui.fragments.IngredientesFragment;
import br.com.agilles.receitas.ui.fragments.ListaReceitasFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceitasActivity extends AppCompatActivity implements ReceitasDelegate {

    WebCliente webCliente = new WebCliente();

    ListaReceitasFragment listaReceitasFragment = new ListaReceitasFragment();
    IngredientesFragment ingredientesFragment = new IngredientesFragment();

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receitas);


        ButterKnife.bind(this);

        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction tx = supportFragmentManager.beginTransaction();
        tx.replace(R.id.frame_principal, listaReceitasFragment);

        if (estaNoModoPaisagem()) {

            tx.replace(R.id.frame_secundario, ingredientesFragment);
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
        Toast.makeText(this, "Vc clicou em uma receita", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void lidaComIngredienteSelecionado(Receita receita) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        if (!estaNoModoPaisagem()) {
            ingredientesFragment = new IngredientesFragment();
            Bundle parametros = new Bundle();
            parametros.putSerializable("receita", receita);
            ingredientesFragment.setArguments(parametros);
            tx.replace(R.id.frame_principal, ingredientesFragment);
            tx.addToBackStack(null);
            tx.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            tx.commit();
        } else {
            IngredientesFragment ingredientesFragment = (IngredientesFragment) manager.findFragmentById(R.id.frame_secundario);
            ingredientesFragment.populaCamposComReceita(receita);
        }

    }

    @Override
    public void lidaComPassosReceitaSelecionada(Receita receita) {
        Toast.makeText(this, "Vc clicou em passos", Toast.LENGTH_SHORT).show();

    }

    @Subscribe
    public void lidaComSucesso(ReceitaEvent event) {
        listaReceitasFragment.populaListaCom(event.listaReceitas);
        mProgressBar.setVisibility(View.INVISIBLE);
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
