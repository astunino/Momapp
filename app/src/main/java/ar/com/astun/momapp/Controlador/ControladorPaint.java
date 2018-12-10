package ar.com.astun.momapp.Controlador;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;

import ar.com.astun.momapp.DAO.DaoDBPaint;
import ar.com.astun.momapp.DAO.DaoInternetPaint;
import ar.com.astun.momapp.DB.DatabaseHelper;
import ar.com.astun.momapp.Modelo.Artist;
import ar.com.astun.momapp.Modelo.Paint;
import ar.com.astun.momapp.Util.ResultListener;
import ar.com.astun.momapp.Util.Util;

public class ControladorPaint{

    public List<Paint> traerPaints(final Context context, final ResultListener<List<Paint>> listenerView) {
        if (Util.isOnline(context)) {
            DaoInternetPaint daoInternetPaint = new DaoInternetPaint();
            daoInternetPaint.traerPaints(new ResultListener<List<Paint>>() {
                @Override
                public void finish(List<Paint> resultado) {

                    DaoDBPaint daoPaint = DatabaseHelper.
                            getInstance(context.getApplicationContext())
                            .daoDBPaint();

                    daoPaint.insertarPaints(resultado);

                    listenerView.finish(resultado);
                }
            });
        } else {

            DaoDBPaint daoPaint = DatabaseHelper.
                    getInstance(context.getApplicationContext())
                    .daoDBPaint();

            List<Paint> paints = daoPaint.getPaints();
            listenerView.finish(paints);

        }
        return null;
    }

}
