package ar.com.astun.momapp.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ar.com.astun.momapp.Modelo.Paint;

@Dao
public interface DaoDBPaint {

    @Insert
    Long insertPaint(Paint paint);


    @Query("SELECT * FROM Paint ORDER BY name asc")
    LiveData<List<Paint>> fetchAllPaints();


    @Query("SELECT * FROM Paint WHERE name =:taskId")
    LiveData<Paint> getPaint(String taskId);


    @Update
    void updatePaint(Paint paint);


    @Delete
    void deletePaint(Paint paint);
}
