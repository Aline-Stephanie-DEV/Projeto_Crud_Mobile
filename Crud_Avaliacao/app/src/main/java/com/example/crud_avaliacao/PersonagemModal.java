package com.example.crud_avaliacao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "personagens_table")
public class PersonagemModal {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String personagemNome;
    public String personagemClasse;
    public String personagemRaca;
    public PersonagemModal(String personagemNome, String personagemClasse, String personagemRaca) {
        this.personagemNome = personagemNome;
        this.personagemClasse = personagemClasse;
        this.personagemRaca = personagemRaca;
    }
    public String getNomePersonagem() {
        return personagemNome;
    }
    public void setNomePersonagem(String personagemNome) {
        this.personagemNome = personagemNome;
    }
    public String getClassePersonagem() {
        return personagemClasse;
    }
    public void setClassePersonagem(String personagemClasse) {
        this.personagemClasse = personagemClasse;
    }
    public String getRacaPersonagem() {
        return personagemRaca;
    }
    public void setRacaPersonagem(String personagemRaca) {
        this.personagemRaca = personagemRaca;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

}
