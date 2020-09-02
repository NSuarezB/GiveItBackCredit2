package com.example.giveitback.src;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonaDao {
    @Query("SELECT * FROM Persona")
    LiveData<List<Persona>> getAll();

    @Query("SELECT * FROM Persona WHERE idPersona=:idPersona")
    Persona find(String idPersona);

    @Query("SELECT * FROM persona WHERE nom LIKE :first AND " +
            "cognom LIKE :last LIMIT 1")
    Persona findByName(String first, String last);

    @Insert
    void insert(Persona persona);

    @Delete
    void delete(Persona persona);
}
