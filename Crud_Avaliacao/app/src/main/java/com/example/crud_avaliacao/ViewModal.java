package com.example.crud_avaliacao;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
public class ViewModal extends AndroidViewModel {
    private PersonagemRepository repository;
    private LiveData<List<PersonagemModal>> allPersonagens;
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new PersonagemRepository(application);
        allPersonagens = repository.getAllPersonagens();
    }
    public void insert(PersonagemModal model) {
        repository.insert(model);
    }
    public void update(PersonagemModal model) {
        repository.update(model);
    }
    public void delete(PersonagemModal model) {
        repository.delete(model);
    }
    public void deleteAllPersonagens() {
        repository.deleteAllPersonagens();
    }
    public LiveData<List<PersonagemModal>> getAllPersonagens() {
        return allPersonagens;
    }
}