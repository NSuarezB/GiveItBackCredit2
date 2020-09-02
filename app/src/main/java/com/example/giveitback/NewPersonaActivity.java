package com.example.giveitback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.giveitback.src.DbClient;
import com.example.giveitback.src.Persona;

import java.util.UUID;

public class NewPersonaActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_input_persones);

        // Submit de la persona
        findViewById(R.id.buttonAfegirPersona).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = ((TextView) findViewById(R.id.txtNomPersona)).getText().toString();
                String cog = ((TextView) findViewById(R.id.txtCognomPersona)).getText().toString();
                String ema = ((TextView) findViewById(R.id.txtMailPersona)).getText().toString();
                String tel = ((TextView) findViewById(R.id.txtPhonePersona)).getText().toString();
                String uid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

                Persona per = new Persona();
                per.idPersona = uid;
                per.nom = nom;
                per.cognom = cog;
                per.correuElectronic = ema;
                per.numTelefon = tel;
                per.img = "";

                DbClient.getClient(getApplicationContext()).personaDao().insert(per);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("idPersona", uid);
                resultIntent.putExtra("nomPersona", nom);

                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

}


