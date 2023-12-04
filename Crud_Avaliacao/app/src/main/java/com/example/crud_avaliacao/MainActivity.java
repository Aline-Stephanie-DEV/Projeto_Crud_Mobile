package com.example.crud_avaliacao;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    private RecyclerView personagensRV;
    private static final int ADD_PERSONAGEM_REQUEST = 1;
    private static final int EDIT_PERSONAGEM_REQUEST = 2;
    private ViewModal viewmodal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personagensRV = findViewById(R.id.idPersonagens);
        FloatingActionButton fab = findViewById(R.id.idBotaoAdd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,
                        NewPersonagemActivity.class);
                startActivityForResult(intent, ADD_PERSONAGEM_REQUEST);
            }
        });
        personagensRV.setLayoutManager(new LinearLayoutManager(this));
        personagensRV.setHasFixedSize(true);
        final PersonagemRVAdapter adapter = new PersonagemRVAdapter();
        personagensRV.setAdapter(adapter);
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);
        viewmodal.getAllPersonagens().observe(this, new Observer<List<PersonagemModal>>() {
            @Override
            public void onChanged(List<PersonagemModal> models) {
                adapter.submitList(models);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull
            RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                viewmodal.delete(adapter.getPersonagemAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Personagem deletado.",
                        Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(personagensRV);
        adapter.setOnItemClickListener(new PersonagemRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PersonagemModal model) {
                Intent intent = new Intent(MainActivity.this, NewPersonagemActivity.class);
                intent.putExtra(NewPersonagemActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewPersonagemActivity.EXTRA_PERSONAGEM_NOME,
                        model.getNomePersonagem());
                intent.putExtra(NewPersonagemActivity.EXTRA_CLASSE,
                        model.getClassePersonagem());
                intent.putExtra(NewPersonagemActivity.EXTRA_RACA,
                        model.getRacaPersonagem());
                startActivityForResult(intent, EDIT_PERSONAGEM_REQUEST);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable
    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_PERSONAGEM_REQUEST && resultCode == RESULT_OK) {
            String personagemNome = data.getStringExtra(NewPersonagemActivity.EXTRA_PERSONAGEM_NOME);
            String personagemClasse = data.getStringExtra(NewPersonagemActivity.EXTRA_CLASSE);
            String personagemRaca = data.getStringExtra(NewPersonagemActivity.EXTRA_RACA);
            PersonagemModal model = new PersonagemModal(personagemNome, personagemClasse, personagemRaca);
            viewmodal.insert(model);
            Toast.makeText(this, "Personagem salvo.", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_PERSONAGEM_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewPersonagemActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Personagem não atualizado.",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            String personagemNome = data.getStringExtra(NewPersonagemActivity.EXTRA_PERSONAGEM_NOME);
            String personagemClasse = data.getStringExtra(NewPersonagemActivity.EXTRA_CLASSE);
            String personagemRaca = data.getStringExtra(NewPersonagemActivity.EXTRA_RACA);
            PersonagemModal model = new PersonagemModal(personagemNome, personagemClasse, personagemRaca);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Personagem atualizado.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Personagem não salvo.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}