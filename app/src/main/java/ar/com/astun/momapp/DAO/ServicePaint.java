package ar.com.astun.momapp.DAO;

import ar.com.astun.momapp.Modelo.ContenedorPaint;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServicePaint {

    @GET("bins/x858r")
    Call<ContenedorPaint> traerPaints();


}
