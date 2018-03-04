package br.com.agilles.receitas.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.ArrayList;

import br.com.agilles.receitas.R;
import br.com.agilles.receitas.ui.activity.ReceitasActivity;

import static br.com.agilles.receitas.ui.widget.AtualizarWidget.LISTA_INGREDIENTES_DA_ACTIVITY;


public class ReceitasWidget extends AppWidgetProvider {

    static ArrayList<String> listaDeIngredientes = new ArrayList<>();


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.receitas_widget);

        Intent abreApp = new Intent(context, ReceitasActivity.class);
        abreApp.addCategory(Intent.ACTION_MAIN);
        abreApp.addCategory(Intent.CATEGORY_LAUNCHER);
        abreApp.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT|Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, abreApp, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.widget_grid_view, pendingIntent);

        Intent intent = new Intent(context, ReceitasWidgetService.class);
        views.setRemoteAdapter(R.id.widget_grid_view, intent);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(context, ReceitasWidget.class));
        final String action = intent.getAction();
        if (action.equals("android.appwidget.action.APPWIDGET_UPDATE2")) {
            listaDeIngredientes = intent.getExtras().getStringArrayList(LISTA_INGREDIENTES_DA_ACTIVITY);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.widget_grid_view);
            ReceitasWidget.update(context, appWidgetManager, appWidgetIds);
            super.onReceive(context, intent);

        }


    }

    private static void update(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }
}

