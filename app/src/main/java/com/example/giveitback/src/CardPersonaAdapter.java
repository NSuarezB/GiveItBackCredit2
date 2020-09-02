package com.example.giveitback.src;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.giveitback.R;
import com.example.giveitback.ViewPersonActivity;
import com.google.android.material.chip.Chip;

import java.util.List;

public class CardPersonaAdapter extends RecyclerView.Adapter<CardPersonaAdapter.MyView> {

    Context context;
    private List<Persona> personas;

    public CardPersonaAdapter(List<Persona> personas, Context context) {
        this.personas = personas;
        this.context = context;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_persona, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        holder.textView.setText(personas.get(position).nom + " " + personas.get(position).cognom);
    }

    @Override
    public int getItemCount() {
        return personas.size();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    // Constructor vista sense visibilitat
    ////////////////////////////////////////////////////////////////////////////////////////////
    public class MyView extends RecyclerView.ViewHolder implements View.OnClickListener {
        Chip textView;

        public MyView(View view) {
            super(view);

            textView = (Chip) view.findViewById(R.id.cardPersonaChip);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Persona persona = personas.get(getAdapterPosition());

            Intent intent = new Intent(context, ViewPersonActivity.class);
            intent.putExtra("idPersona", persona.idPersona);
            context.startActivity(intent);
        }
    }
}
