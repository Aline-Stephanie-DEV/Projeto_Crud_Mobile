package com.example.crud_avaliacao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;
@androidx.room.Dao
public interface Dao {
    @Insert
    void insert(PersonagemModal model);

    @Update
    void update(PersonagemModal model);
    @Delete
    void delete(PersonagemModal model);
    @Query("DELETE FROM personagens_table")
    void deleteAllPersonagens();
    @Query("SELECT * FROM personagens_table ORDER BY personagemNome ASC")
    LiveData<List<PersonagemModal>> getAllPersonagens();
}