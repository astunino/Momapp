package ar.com.astun.momapp.DB;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import ar.com.astun.momapp.Modelo.Paint;

public class PaintRepositorio {

    private String DB_NAME = "db_paint";
    private AppDatabase appDatabase;


    public PaintRepositorio(Context context) {
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    public void insertPaint(String image, String name, String artistId) {
        Paint paint = new Paint();
        paint.setImage(image);
        paint.setName(name);
        paint.setArtistId(artistId);

        insertPaint(paint);
    }

    public void insertPaint(final Paint paint) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.daoDBPaint().insertPaint(paint);
                return null;
            }
        }.execute();
    }

    public void updatePaint(final Paint paint) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                appDatabase.daoDBPaint().updatePaint(paint);
                return null;
            }
        }.execute();
    }

    public void deletePaint(final Integer id) {
        final LiveData<Paint> paint = getPaint(id);
        if(paint != null) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    appDatabase.daoDBPaint().deletePaint(paint.getValue());
                    return null;
                }
            }.execute();
        }
    }

    public LiveData<Paint> getPaint(Integer id) {
        return appDatabase.daoDBPaint().getPaint(id);
    }

    public LiveData<List<Paint>> getPaints() {
        return appDatabase.daoDBPaint().fetchAllPaints();
    }
}
