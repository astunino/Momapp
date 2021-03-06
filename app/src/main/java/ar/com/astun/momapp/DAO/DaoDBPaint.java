package ar.com.astun.momapp.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ar.com.astun.momapp.Modelo.Paint;

@Dao
public interface DaoDBPaint {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertarPaint(Paint paint);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertarPaints(List<Paint> paints);

    @Query("SELECT * FROM Paint")
    List<Paint> getPaints();
}
