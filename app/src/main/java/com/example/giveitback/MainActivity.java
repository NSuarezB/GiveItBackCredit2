package com.example.giveitback;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giveitback.src.AppDatabase;
import com.example.giveitback.src.CardObjectAdapter;
import com.example.giveitback.src.CardPersonaAdapter;
import com.example.giveitback.src.CardPrestecAdapter;
import com.example.giveitback.src.DbClient;
import com.example.giveitback.src.Objecte;
import com.example.giveitback.src.Persona;
import com.example.giveitback.src.Prestec;
import com.example.giveitback.src.PrestecComplet;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ////////////////////////////////////////////////////////////////////////////////////////////
        // Predeterminat per la app
        ////////////////////////////////////////////////////////////////////////////////////////////
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        ////////////////////////////////////////////////////////////////////////////////////////////
        // RecycleViews + Observers de la db
        ////////////////////////////////////////////////////////////////////////////////////////////
        AppDatabase app = DbClient.getClient(getApplicationContext());

        final RecyclerView recyclerViewPrestec = (RecyclerView) findViewById(R.id.recycleViewPrestec);
        app.prestecDao().getAllComplet().observe(this, new Observer<List<PrestecComplet>>() {
            @Override
            public void onChanged(List<PrestecComplet> prestecs) {
                LinearLayoutManager horizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerViewPrestec.setLayoutManager(horizontalLayout);
                recyclerViewPrestec.setAdapter(new CardPrestecAdapter(prestecs, MainActivity.this));
            }
        });
        final RecyclerView recyclerViewObjecte = (RecyclerView) findViewById(R.id.recycleViewObject);
        app.objecteDao().getAll().observe(this, new Observer<List<Objecte>>() {
            @Override
            public void onChanged(List<Objecte> objectes) {
                LinearLayoutManager horizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerViewObjecte.setLayoutManager(horizontalLayout);
                recyclerViewObjecte.setAdapter(new CardObjectAdapter(objectes, MainActivity.this));
            }
        });
        final RecyclerView recyclerViewPersona = (RecyclerView) findViewById(R.id.recycleViewPersones);
        app.personaDao().getAll().observe(this, new Observer<List<Persona>>() {
            @Override
            public void onChanged(List<Persona> personas) {
                LinearLayoutManager horizontalLayout = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerViewPersona.setLayoutManager(horizontalLayout);
                recyclerViewPersona.setAdapter(new CardPersonaAdapter(personas, MainActivity.this));
            }
        });


        ////////////////////////////////////////////////////////////////////////////////////////////
        // Esdeveniments click
        ////////////////////////////////////////////////////////////////////////////////////////////
        findViewById(R.id.fabOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create new fragment and transaction
            /*    Fragment newFragment = new NewPrestecFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack if needed
                transaction.replace(R.id.nav_host_fragment, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();*/

                Intent intent = new Intent(MainActivity.this, NewPrestecActivity.class);
                startActivity(intent);

            }
        });

    }

}