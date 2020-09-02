package com.example.giveitback;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.giveitback.src.AppDatabase;
import com.example.giveitback.src.DbClient;
import com.example.giveitback.src.Persona;

public class ViewPersonActivity extends Activity {

    Persona persona;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Predeterminat per la app
        ////////////////////////////////////////////////////////////////////////////////////////////
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_persona);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Obtenim el ID a mostrar o sortim del intent amb error
        ////////////////////////////////////////////////////////////////////////////////////////////
        final AppDatabase app = DbClient.getClient(getApplicationContext());
        String idPersona = this.getIntent().getStringExtra("idPersona");

        Persona persona = app.personaDao().find(idPersona);
        if (persona == null) {
            finishActivity(RESULT_CANCELED);
            return;
        }

        // Persona
        ((TextView) findViewById(R.id.txtPersonaNom)).setText(persona.nom + " " + persona.cognom);
        ((TextView) findViewById(R.id.txtPersonaEmail)).setText(persona.correuElectronic);
        ((TextView) findViewById(R.id.txtPersonaTelefon)).setText(persona.numTelefon);

    }
}
