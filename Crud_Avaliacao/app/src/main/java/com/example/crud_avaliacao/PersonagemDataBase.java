package com.example.crud_avaliacao;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
@Database(entities = {PersonagemModal.class}, version = 1)
public abstract class PersonagemDataBase  extends RoomDatabase {
    private static PersonagemDataBase instance;
    public abstract Dao Dao();
    public static synchronized PersonagemDataBase getInstance(Context context) {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(),
                             PersonagemDataBase.class, "personagem_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new
            RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                            new PopulateDbAsyncTask(instance).execute();
                }
            };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void,
            Void> {
        PopulateDbAsyncTask(PersonagemDataBase instance) {
            Dao dao = instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}


