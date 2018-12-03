package ar.com.astun.momapp.Controlador;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import ar.com.astun.momapp.DAO.DaoInternetPaint;
import ar.com.astun.momapp.DB.PaintRepositorio;
import ar.com.astun.momapp.Modelo.Paint;
import ar.com.astun.momapp.Util.ResultListener;
import ar.com.astun.momapp.Util.Util;

public class ControladorPaint{

    public LiveData<List<Paint>> traerPaints(final Context context, final ResultListener<List<Paint>> listenerView) {
        if (Util.isOnline(context)) {
            DaoInternetPaint daoInternetPaint = new DaoInternetPaint();
            daoInternetPaint.traerPaints(new ResultListener<List<Paint>>() {
                @Override
                public void finish(List<Paint> resultado) {


                  PaintRepositorio paintRepository = new PaintRepositorio(context);

                    for(int i=0;i<resultado.size();i++){

                        String imagen = resultado.get(i).getImage();
                        String name = resultado.get(i).getName();
                        String artistId = resultado.get(i).getArtistId();

                        paintRepository.insertPaint(imagen,name,artistId);

                    }


                    listenerView.finish(resultado);
                }
            });
        } else {

           /*
            PaintRepositorio paintRepository = new PaintRepositorio(context);

            paintRepository.getPaints().observe(this, new Observer<List<Paint>>() {
                @Override
                public void onChanged(@Nullable List<Paint> paints) {
                    listenerView.finish(paints);
                }
            });*/


        }
        return null;
    }

}
