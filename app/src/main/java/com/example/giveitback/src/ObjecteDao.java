package com.example.giveitback.src;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ObjecteDao {

    @Query("SELECT * FROM objecte")
    LiveData<List<Objecte>> getAll();

    @Query("SELECT * FROM Objecte WHERE idObjecte=:idObjecte")
    Objecte find(String idObjecte);

    @Query("SELECT * FROM objecte WHERE nom LIKE :nom LIMIT 1")
    Objecte findByName(String nom);

    @Insert
    void insert(Objecte objecte);

    @Delete
    void delete(Objecte objecte);
}
