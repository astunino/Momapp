package ar.com.astun.momapp.Controlador;

import android.content.Context;

import java.util.List;

import ar.com.astun.momapp.DAO.DaoInternetPaint;
import ar.com.astun.momapp.Modelo.Paint;
import ar.com.astun.momapp.Util.ResultListener;
import ar.com.astun.momapp.Util.Util;

public class ControladorPaint {

    public void traerPaints(Context context, final ResultListener<List<Paint>> listenerView) {
        if (Util.isOnline(context)) {
            DaoInternetPaint daoInternetPaint = new DaoInternetPaint();
            daoInternetPaint.traerPaints(new ResultListener<List<Paint>>() {
                @Override
                public void finish(List<Paint> resultado) {
                    listenerView.finish(resultado);
                }
            });
        } else {
            // BASE DE DATOS
        }
    }
}
