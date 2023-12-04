package com.example.crud_avaliacao;


import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;
public class PersonagemRepository {
    private Dao dao;
    private LiveData<List<PersonagemModal>> allPersonagens;
    public PersonagemRepository(Application application) {
        PersonagemDataBase database = PersonagemDataBase.getInstance(application);
        dao = database.Dao();
        allPersonagens = dao.getAllPersonagens();
    }
    public void insert(PersonagemModal model) {
        new InsertPersonagemAsyncTask(dao).execute(model);
    }

    public void update(PersonagemModal model) {
        new UpdatePersonagemAsyncTask(dao).execute(model);
    }
    public void delete(PersonagemModal model) {
        new DeletePersonagemAsyncTask(dao).execute(model);
    }
    public void deleteAllPersonagens() {
        new DeleteAllPersonagensAsyncTask(dao).execute();
    }
    public LiveData<List<PersonagemModal>> getAllPersonagens() {
        return allPersonagens;
    }
    private static class InsertPersonagemAsyncTask extends AsyncTask<PersonagemModal,
            Void, Void> {
        private Dao dao;
        private InsertPersonagemAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(PersonagemModal... model) {
            dao.insert(model[0]);
            return null;
        }
    }
    private static class UpdatePersonagemAsyncTask extends AsyncTask<PersonagemModal,
            Void, Void> {
        private Dao dao;
        private UpdatePersonagemAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(PersonagemModal... models) {
            dao.update(models[0]);
            return null;
        }
    }
    private static class DeletePersonagemAsyncTask extends AsyncTask<PersonagemModal,
            Void, Void> {
        private Dao dao;
        private DeletePersonagemAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(PersonagemModal... models) {
            dao.delete(models[0]);
            return null;
        }
    }
    private static class DeleteAllPersonagensAsyncTask extends AsyncTask<Void,
            Void, Void> {
        private Dao dao;
        private DeleteAllPersonagensAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllPersonagens();
            return null;
        }
    }
}
