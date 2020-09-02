package com.example.giveitback.src;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.giveitback.R;
import com.example.giveitback.ViewPrestecActivity;

import java.util.List;

public class CardPrestecAdapter extends RecyclerView.Adapter<CardPrestecAdapter.MyView> {

    Context context;
    private List<PrestecComplet> prestecs;

    public CardPrestecAdapter(List<PrestecComplet> prestecs, Context context) {
        this.prestecs = prestecs;
        this.context = context;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_prestec, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        holder.nomPersona.setText(prestecs.get(position).persona.nom);
        holder.nomObjecte.setText(prestecs.get(position).objecte.nom);
        holder.dataPrestec.setText(prestecs.get(position).prestec.dataPrestec);
    }

    @Override
    public int getItemCount() {
        return prestecs.size();
    }

    public class MyView extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nomPersona;
        TextView nomObjecte;
        TextView dataPrestec;
//        TextView dataRetorn;

        public MyView(View view) {
            super(view);

            nomPersona = (TextView) view.findViewById(R.id.cardPrestecPersonaNom);
            nomObjecte = (TextView) view.findViewById(R.id.cardPrestecObjectNom);
            dataPrestec = (TextView) view.findViewById(R.id.cardPrestecDataPrestec);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            PrestecComplet prestec = prestecs.get(getAdapterPosition());

            Intent intent = new Intent(context, ViewPrestecActivity.class);
            intent.putExtra("idPrestec", prestec.prestec.idPrestec);
            context.startActivity(intent);
        }
    }
}
