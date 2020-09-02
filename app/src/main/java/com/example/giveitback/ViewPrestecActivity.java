package com.example.giveitback;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.giveitback.src.AppDatabase;
import com.example.giveitback.src.DbClient;
import com.example.giveitback.src.PrestecComplet;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Date;

public class ViewPrestecActivity extends Activity {

    PrestecComplet prestec;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Predeterminat per la app
        ////////////////////////////////////////////////////////////////////////////////////////////
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_prestec);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Obtenim el ID a mostrar o sortim del intent amb error
        ////////////////////////////////////////////////////////////////////////////////////////////
        final AppDatabase app = DbClient.getClient(getApplicationContext());
        String idPrestec = this.getIntent().getStringExtra("idPrestec");

        prestec = app.prestecDao().findComplet(idPrestec);
        if (prestec == null) {
            finishActivity(RESULT_CANCELED);
            return;
        }

        // Estat
        ((TextView) findViewById(R.id.txtPrestecEstat)).setText(prestec.prestec.dataRetorn == null ? "(NO RETORNAT)" : "(RETORNAT)");
        ((TextView) findViewById(R.id.txtPrestecDataPrestec)).setText(prestec.prestec.dataPrestec);
        ((TextView) findViewById(R.id.txtPrestecDataRetorn)).setText(prestec.prestec.dataRetorn == null ? "-" : prestec.prestec.dataRetorn);

        // Persona
        ((TextView) findViewById(R.id.txtPrestecPersonaNom)).setText(prestec.persona.nom + " " + prestec.persona.cognom);
        ((TextView) findViewById(R.id.txtPrestecPersonaEmail)).setText(prestec.persona.correuElectronic);
        ((TextView) findViewById(R.id.txtPrestecPersonaTelefon)).setText(prestec.persona.numTelefon);

        // Objecte
        ((TextView) findViewById(R.id.txtPrestecObjecteNom)).setText(prestec.objecte.nom);
        ((TextView) findViewById(R.id.txtPrestecObjecteDefinicio)).setText(prestec.objecte.definicio);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Esdeveniments retornat o esborrar
        ////////////////////////////////////////////////////////////////////////////////////////////
        findViewById(R.id.btnPrestecRetornat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prestec.prestec.dataRetorn = (new Date()).toString();
                int res = app.prestecDao().setRetornat(prestec.prestec.idPrestec, prestec.prestec.dataRetorn);

                if (res == 0) {

                }

                finish();
                startActivity(getIntent());
            }
        });
        findViewById(R.id.btnPrestecEsborrar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialAlertDialogBuilder(getApplicationContext())
                        .setTitle("Esborrar")
                        .setMessage("De debó vols esborrar aquest préstec?")
                        .setPositiveButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                app.prestecDao().delete(prestec.prestec);

                                finish();

//                                Snackbar.make(view, "Préstec esborrat correctament").show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });
    }
}
