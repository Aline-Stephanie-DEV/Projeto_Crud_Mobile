package com.example.crud_avaliacao;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
public class NewPersonagemActivity extends AppCompatActivity {
    private EditText personagemNomeEdt, personagemClasseEdt, personagemRacaEdt;
    private Button personagemBtn;
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_PERSONAGEM_NOME = "com.gtappdevelopers.gfgroomdatabase.EXTRA_PERSONAGEM_NOME";
    public static final String EXTRA_CLASSE = "com.gtappdevelopers.gfgroomdatabase.EXTRA_PERSONAGEM_CLASSE";
    public static final String EXTRA_RACA = "com.gtappdevelopers.gfgroomdatabase.EXTRA_PERSONAGEM_RACA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_personagem);
        personagemNomeEdt = findViewById(R.id.idEdtNomePersonagem);
        personagemClasseEdt = findViewById(R.id.idEdtClassePersonagem);
        personagemRacaEdt = findViewById(R.id.idEdtRacaPersonagem);
        personagemBtn = findViewById(R.id.idBtnSavePersonagem);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            personagemNomeEdt.setText(intent.getStringExtra(EXTRA_PERSONAGEM_NOME));
            personagemClasseEdt.setText(intent.getStringExtra(EXTRA_CLASSE));
            personagemRacaEdt.setText(intent.getStringExtra(EXTRA_RACA));
        }

        personagemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String personagemNome = personagemNomeEdt.getText().toString();
                String personagemClasse = personagemClasseEdt.getText().toString();
                String personagemRaca = personagemRacaEdt.getText().toString();
                if (personagemNome.isEmpty() || personagemClasse.isEmpty() ||
                        personagemRaca.isEmpty()) {
                    Toast.makeText(NewPersonagemActivity.this, "Entre com os detalhes do personagem.", Toast.LENGTH_SHORT).show();
                    return;
                }

                savePersonagem(personagemNome, personagemClasse, personagemRaca);
            }
        });
    }
    private void savePersonagem(String personagemNome, String personagemClasse,
                            String personagemRaca) {
        Intent data = new Intent();
        data.putExtra(EXTRA_PERSONAGEM_NOME, personagemNome);
        data.putExtra(EXTRA_CLASSE, personagemClasse);
        data.putExtra(EXTRA_RACA, personagemRaca);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);

        Toast.makeText(this, "Personagem salvo no banco de dados Room.",
                Toast.LENGTH_SHORT).show();
    }
}