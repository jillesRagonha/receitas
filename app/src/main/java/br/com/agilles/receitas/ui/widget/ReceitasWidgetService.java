package br.com.agilles.receitas.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.List;

import br.com.agilles.receitas.R;

import static br.com.agilles.receitas.ui.widget.ReceitasWidget.listaDeIngredientes;

/**
 * Created by jille on 03/03/2018.
 */

public class ReceitasWidgetService extends RemoteViewsService {

    List<String> listaIngredientesRemoteView;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewFactory(this.getApplicationContext(),intent);
    }




    class GridRemoteViewFactory implements RemoteViewsFactory {

        Context context = null;

        public GridRemoteViewFactory(Context context,Intent intent) {
            this.context = context;

        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            listaIngredientesRemoteView = listaDeIngredientes;

        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return listaIngredientesRemoteView.size();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_ingredientes_item);
            views.setTextViewText(R.id.widget_texto, listaIngredientesRemoteView.get(position));
            Intent preencherIntent = new Intent();
            views.setOnClickFillInIntent(R.id.widget_texto, preencherIntent);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }

}
