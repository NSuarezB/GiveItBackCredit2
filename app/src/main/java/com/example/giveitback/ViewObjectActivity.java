package com.example.giveitback;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.giveitback.src.AppDatabase;
import com.example.giveitback.src.DbClient;
import com.example.giveitback.src.Objecte;

public class ViewObjectActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Predeterminat per la app
        ////////////////////////////////////////////////////////////////////////////////////////////
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_object);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Obtenim el ID a mostrar o sortim del intent amb error
        ////////////////////////////////////////////////////////////////////////////////////////////
        final AppDatabase app = DbClient.getClient(getApplicationContext());
        String idObjecte = this.getIntent().getStringExtra("idObjecte");

        Objecte objecte = app.objecteDao().find(idObjecte);
        if (objecte == null) {
            finishActivity(RESULT_CANCELED);
            return;
        }

        // Objecte
        ((TextView) findViewById(R.id.txtObjecteNom)).setText(objecte.nom);
        ((TextView) findViewById(R.id.txtObjecteDefinicio)).setText(objecte.definicio);
    }
}
