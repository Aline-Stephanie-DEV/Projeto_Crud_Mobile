package com.example.crud_avaliacao;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
public class PersonagemRVAdapter extends ListAdapter<PersonagemModal,
        PersonagemRVAdapter.ViewHolder> {
    private OnItemClickListener listener;
    PersonagemRVAdapter() {
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback<PersonagemModal> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<PersonagemModal>() {
                @Override
                public boolean areItemsTheSame(PersonagemModal oldItem, PersonagemModal
                        newItem) {
                    return oldItem.getId() == newItem.getId();
                }
                @Override
                public boolean areContentsTheSame(PersonagemModal oldItem, PersonagemModal
                        newItem) {
                    return oldItem.getNomePersonagem().equals(newItem.getNomePersonagem()) &&
                            oldItem.getClassePersonagem().equals(newItem.getClassePersonagem()) &&
                            oldItem.getRacaPersonagem().equals(newItem.getRacaPersonagem());
                }
            };
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int
            viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.personagem_rv_item, parent, false);
        return new ViewHolder(item);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PersonagemModal model = getPersonagemAt(position);
        holder.personagemNomeTV.setText(model.getNomePersonagem());
        holder.personagemClasseTV.setText(model.getClassePersonagem());
        holder.personagemRacaTV.setText(model.getRacaPersonagem());
    }
    public PersonagemModal getPersonagemAt(int position) {
        return getItem(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView personagemNomeTV, personagemClasseTV, personagemRacaTV;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            personagemNomeTV = itemView.findViewById(R.id.idTVNomePersonagem);
            personagemClasseTV = itemView.findViewById(R.id.idTVClassePersonagem);
            personagemRacaTV = itemView.findViewById(R.id.idTVRacaPersonagem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position !=
                            RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(PersonagemModal model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}