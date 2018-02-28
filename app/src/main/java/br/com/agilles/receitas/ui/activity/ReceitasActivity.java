package br.com.agilles.receitas.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import br.com.agilles.receitas.ui.fragments.PassosFragment;
import br.com.agilles.receitas.ui.fragments.PlayerFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceitasActivity extends AppCompatActivity implements ReceitasDelegate {

    WebCliente webCliente = new WebCliente();

    private static final String INGREDIENTE_TAG = "ingrediente_fragment";
    private static final String PASSOS_TAG = "passos_fragment";
    private int fragmentoExibido;

    private static final int RECEITA_INGREDIENTES = 1;
    private static final int RECEITA_PASSOS = 2;
    private static final int RECEITA_VIDEOS = 3;

    ListaReceitasFragment listaReceitasFragment = new ListaReceitasFragment();
    IngredientesFragment ingredientesFragment = new IngredientesFragment();
    PassosFragment passosFragment = new PassosFragment();
    Fragment fragment = new Fragment();

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

        if (savedInstanceState != null) {
            tx.replace(R.id.frame_principal, listaReceitasFragment);
            fragmentoExibido = savedInstanceState.getInt("fragmentoExibido");

            ingredientesFragment = (IngredientesFragment) getSupportFragmentManager().getFragment(savedInstanceState, INGREDIENTE_TAG);
            passosFragment = (PassosFragment) getSupportFragmentManager().getFragment(savedInstanceState, PASSOS_TAG);

            if (estaNoModoPaisagem()) {
                switch (fragmentoExibido) {
                    case RECEITA_INGREDIENTES:
                        tx.replace(R.id.frame_secundario, ingredientesFragment);
                        break;
                    case RECEITA_PASSOS:
                        tx.replace(R.id.frame_secundario, passosFragment);
                        break;
                    default:
                        tx.replace(R.id.frame_secundario, new IngredientesFragment());
                }
            }

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
        ingredientesFragment = new IngredientesFragment();
        Bundle parametros = new Bundle();
        parametros.putSerializable("receita", receita);
        ingredientesFragment.setArguments(parametros);
        tx.addToBackStack(null);

        if (!estaNoModoPaisagem()) {
            tx.replace(R.id.frame_principal, ingredientesFragment);
        } else {
            tx.replace(R.id.frame_secundario, ingredientesFragment);
        }
        tx.commit();
        fragmentoExibido = RECEITA_INGREDIENTES;
    }


    @Override
    public void lidaComPassosReceitaSelecionada(Receita receita) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        passosFragment = new PassosFragment();
        fragment = new PassosFragment();
        Bundle parametros = new Bundle();
        parametros.putSerializable("receita", receita);
        passosFragment.setArguments(parametros);
        fragment.setArguments(parametros);
        if (!estaNoModoPaisagem()) {
            tx.replace(R.id.frame_principal, passosFragment);
            tx.addToBackStack(null);
        } else {
            tx.replace(R.id.frame_secundario, passosFragment);
        }
        tx.commit();
        fragmentoExibido = RECEITA_PASSOS;

    }

    @Override
    public void lidaComVideoDoPasso(String video) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        PlayerFragment playerFragment = new PlayerFragment();
        Bundle parametros = new Bundle();
        parametros.putString("endereco", video);
        playerFragment.setArguments(parametros);
        fragment = new PlayerFragment();
        fragment.setArguments(parametros);

        if (!estaNoModoPaisagem()) {
            tx.replace(R.id.frame_principal, playerFragment);
        } else {
            tx.replace(R.id.frame_secundario, playerFragment);
        }
        tx.addToBackStack(null);
        fragmentoExibido = RECEITA_VIDEOS;

        tx.commit();
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        switch (fragmentoExibido) {
            case RECEITA_INGREDIENTES:
                getSupportFragmentManager().putFragment(outState, INGREDIENTE_TAG, ingredientesFragment);
                outState.putInt("fragmentoExibido", fragmentoExibido);
                break;
            case RECEITA_PASSOS:
                getSupportFragmentManager().putFragment(outState, PASSOS_TAG, passosFragment);
                outState.putInt("fragmentoExibido", fragmentoExibido);
                break;

        }
    }
}


