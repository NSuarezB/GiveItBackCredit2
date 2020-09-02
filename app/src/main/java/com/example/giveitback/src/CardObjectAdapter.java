package com.example.giveitback.src;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.giveitback.R;
import com.example.giveitback.ViewObjectActivity;

import java.util.List;

public class CardObjectAdapter extends RecyclerView.Adapter<CardObjectAdapter.MyView> {

    Context context;
    private List<Objecte> objectes;

    public CardObjectAdapter(List<Objecte> objectes, Context context) {
        this.objectes = objectes;
        this.context = context;
    }

    @Override
    public MyView onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_object, parent, false);

        return new MyView(itemView);
    }

    @Override
    public void onBindViewHolder(final MyView holder, final int position) {

        holder.textView.setText(objectes.get(position).nom);
    }

    @Override
    public int getItemCount() {
        return objectes.size();
    }

    public class MyView extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;

        public MyView(View view) {
            super(view);

            textView = (TextView) view.findViewById(R.id.cardObjectNom);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Objecte objecte = objectes.get(getAdapterPosition());

            Intent intent = new Intent(context, ViewObjectActivity.class);
            intent.putExtra("idObjecte", objecte.idObjecte);
            context.startActivity(intent);
        }
    }
}
