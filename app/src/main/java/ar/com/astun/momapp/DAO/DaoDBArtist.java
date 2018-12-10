package ar.com.astun.momapp.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import ar.com.astun.momapp.Modelo.Artist;

@Dao
public interface DaoDBArtist {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertarArtist(Artist artist);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertarArtists(List<Artist> artists);


    @Query("SELECT * FROM Artist")
    List<Artist> getArtists();
}
