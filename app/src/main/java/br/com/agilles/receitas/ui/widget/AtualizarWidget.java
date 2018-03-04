package br.com.agilles.receitas.ui.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by jille on 03/03/2018.
 */

public class AtualizarWidget extends IntentService {

    public static String ACTION_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE2";
    public static String LISTA_INGREDIENTES_DA_ACTIVITY = "LISTA_INGREDIENTES_DA_ACTIVITY";
    private static final String TAG = "AtualizarWidget";

    public AtualizarWidget() {
        super(TAG);
    }

    public AtualizarWidget(String name) {
        super(TAG);
    }

    public static void inicializarServico(Context context, ArrayList<String> listaIngredientes) {
        Intent intent = new Intent(context, AtualizarWidget.class);
        intent.putExtra(LISTA_INGREDIENTES_DA_ACTIVITY, listaIngredientes);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            ArrayList<String> listaIngredientes = intent.getExtras().getStringArrayList(LISTA_INGREDIENTES_DA_ACTIVITY);
            atualizarWidget(listaIngredientes);

        }
    }

    private void atualizarWidget(ArrayList<String> listaIngredientes) {
        Intent intent = new Intent(getBaseContext(), ReceitasWidget.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(LISTA_INGREDIENTES_DA_ACTIVITY, listaIngredientes);
        sendBroadcast(intent);
    }
}
