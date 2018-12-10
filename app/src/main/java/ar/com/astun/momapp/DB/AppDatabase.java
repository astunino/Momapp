package ar.com.astun.momapp.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ar.com.astun.momapp.DAO.DaoDBArtist;
import ar.com.astun.momapp.DAO.DaoDBPaint;
import ar.com.astun.momapp.Modelo.Artist;
import ar.com.astun.momapp.Modelo.Paint;

@Database(entities = {Paint.class,Artist.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DaoDBPaint daoDBPaint();
    public abstract DaoDBArtist daoDBArtist();
}

