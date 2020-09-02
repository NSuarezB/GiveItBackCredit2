package com.example.giveitback;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;

import com.example.giveitback.src.AppDatabase;
import com.example.giveitback.src.DbClient;
import com.example.giveitback.src.Objecte;
import com.example.giveitback.src.Persona;
import com.example.giveitback.src.Prestec;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class NewPrestecActivity extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();

    String selectedObjecte = "";
    String selectedPersona = "";

    List<Persona> personasDb;
    List<Objecte> objectesDb;

    ActivityResultLauncher<Intent> mGeneratePersona = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.i("ADDED", "Persona amb id: " + result.getData().getStringExtra("idPersona"));
                        selectedPersona = result.getData().getStringExtra("idPersona");
                    }

                    ((Spinner) findViewById(R.id.spinnerPersones)).setSelection(((Spinner) findViewById(R.id.spinnerPersones)).getCount() - 2);
                }
            });
    ActivityResultLauncher<Intent> mGenerateObject = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Log.i("ADDED", "Objecte amb id: " + result.getData().getStringExtra("idObjecte"));
                        selectedObjecte = result.getData().getStringExtra("idObjecte");
                    }

                    ((Spinner) findViewById(R.id.spinnerObjectes)).setSelection(((Spinner) findViewById(R.id.spinnerObjectes)).getCount() - 2);
                }
            });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Predeterminat per la app
        ////////////////////////////////////////////////////////////////////////////////////////////
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_prestec);
        ConstraintLayout coordinatorLayout = (ConstraintLayout) findViewById(R.id.layNewPrestec);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Nou préstec");

//        (EditText) findViewById(R.id.datepickerPrestec)

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Submit del prestec
        ////////////////////////////////////////////////////////////////////////////////////////////
        findViewById(R.id.buttonAfegirPrestec).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedPersona.equals("") || selectedObjecte.equals("")) {
                    Snackbar.make(coordinatorLayout, "Persona i Objecte son obligatoris", Snackbar.LENGTH_LONG).show();
                    return;
                }

                String idp = selectedPersona;
                String ido = selectedObjecte;

                String dat = ((TextView) findViewById(R.id.datepickerPrestec)).getText().toString();
                String not = ((TextView) findViewById(R.id.txtNotaPrestec)).getText().toString();
                String uid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

                Prestec pre = new Prestec();
                pre.idPrestec = uid;
                pre.idObjecte = ido;
                pre.idPersona = idp;

                pre.dataPrestec = dat;
                pre.dataRetorn = null;

//                pre.notaAddicional = not;

                DbClient.getClient(getApplicationContext()).prestecDao().insert(pre);

                finish();
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Events per afegir persones o objectes
        ////////////////////////////////////////////////////////////////////////////////////////////
        ((Spinner) findViewById(R.id.spinnerPersones)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Afegeix nova persona...")) {
                    Intent intent = new Intent(NewPrestecActivity.this, NewPersonaActivity.class);
                    mGeneratePersona.launch(intent);
                    return;
                }

                selectedPersona = personasDb.get(position).idPersona;

                Log.i("ADDED", "Selected person " + personasDb.get(position).nom);
            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ((Spinner) findViewById(R.id.spinnerObjectes)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Afegeix nou objecte...")) {
                    Intent intent = new Intent(NewPrestecActivity.this, NewObjecteActivity.class);
                    mGenerateObject.launch(intent);
                    return;
                }

                selectedObjecte = objectesDb.get(position).idObjecte;

                Log.i("ADDED", "Selected object " + objectesDb.get(position).nom);
            } // to close the onItemSelected

            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        // Observadors DB per a persones i objectes
        ////////////////////////////////////////////////////////////////////////////////////////////
        AppDatabase app = DbClient.getClient(getApplicationContext());
        app.personaDao().getAll().observe(this, new Observer<List<Persona>>() {
            @Override
            public void onChanged(List<Persona> personas) {
                personasDb = personas;

                ArrayAdapter<Persona> adapter = new ArrayAdapter<Persona>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, personas);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                Persona p = new Persona();
                p.idPersona = "0";
                p.nom = "Afegeix nova persona...";
                adapter.add(p);

                ((Spinner) findViewById(R.id.spinnerPersones)).setAdapter(adapter);
            }
        });
        app.objecteDao().getAll().observe(this, new Observer<List<Objecte>>() {
            @Override
            public void onChanged(List<Objecte> objectes) {
                objectesDb = objectes;

                ArrayAdapter<Objecte> adapter = new ArrayAdapter<Objecte>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, objectes);
                adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                Objecte o = new Objecte();
                o.idObjecte = "0";
                o.nom = "Afegeix nou objecte...";
                adapter.add(o);

                ((Spinner) findViewById(R.id.spinnerObjectes)).setAdapter(adapter);
            }
        });

        ////////////////////////////////////////////////////////////////////////////////////////////
        // Data prestec
        ////////////////////////////////////////////////////////////////////////////////////////////
        final EditText edittext = findViewById(R.id.datepickerPrestec);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };
        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(NewPrestecActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


}
