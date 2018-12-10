package ar.com.astun.momapp.DB;

import android.arch.persistence.room.Room;
import android.content.Context;


public class DatabaseHelper {
    private static final String DB_NAME = "db_paint.db";
    private static AppDatabase db;

    public static AppDatabase getInstance(Context applicationContext){
        if (db == null){
            db = Room.databaseBuilder(applicationContext,
                    AppDatabase.class, DB_NAME).allowMainThreadQueries().build();
        }
        return db;
    }
}
