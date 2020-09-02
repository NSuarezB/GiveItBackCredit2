package com.example.giveitback.src;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Prestec.class, Objecte.class, Persona.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PrestecDao prestecDao();

    public abstract ObjecteDao objecteDao();

    public abstract PersonaDao personaDao();
}