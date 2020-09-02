package com.example.giveitback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.giveitback.src.DbClient;
import com.example.giveitback.src.Objecte;

import java.util.UUID;


public class NewObjecteActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_input_object);

        // Submit de l'objecte
        findViewById(R.id.buttonAfegirObjecte).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = ((TextView) findViewById(R.id.txtObjecte)).getText().toString();
                String def = ((TextView) findViewById(R.id.txtDefinicio)).getText().toString();
                String uid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

                Objecte obj = new Objecte();
                obj.idObjecte = uid;
                obj.nom = nom;
                obj.definicio = def;
                obj.img = "";

                DbClient.getClient(getApplicationContext()).objecteDao().insert(obj);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("idObjecte", uid);
                resultIntent.putExtra("nomObjecte", nom);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

}

