package ar.com.astun.momapp.DAO;
import java.util.List;

import ar.com.astun.momapp.Modelo.ContenedorPaint;
import ar.com.astun.momapp.Modelo.Paint;
import ar.com.astun.momapp.Util.ResultListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaoInternetPaint extends DaoHelper {
    private static final String BASE_URL = "http://api.myjson.com/";

    private ServicePaint servicePaint;

    // Constructor
    public DaoInternetPaint() {
        super(BASE_URL);

        servicePaint = retrofit.create(ServicePaint.class);
    }


    public void traerPaints(final ResultListener<List<Paint>> listenerController) {

        Call<ContenedorPaint> call = servicePaint.traerPaints();

        call.enqueue(new Callback<ContenedorPaint>() {
            @Override
            public void onResponse(Call<ContenedorPaint> call, Response<ContenedorPaint> response) {

                if (response.code() >= 200 && response.code() < 300) {
                    ContenedorPaint contenedorPaint = response.body();
                    listenerController.finish(contenedorPaint.getPaints());
                }


            }

            @Override
            public void onFailure(Call<ContenedorPaint> call, Throwable t) {
                //TODO Completar

            }
        });
    }
}
