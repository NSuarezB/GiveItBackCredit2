package com.example.giveitback.src;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PrestecDao {
    @Query("SELECT * FROM Prestec")
    LiveData<List<Prestec>> getAll();

    @Query("SELECT * FROM Prestec")
    LiveData<List<PrestecComplet>> getAllComplet();

    @Query("SELECT * FROM Prestec WHERE idPrestec=:idPrestec")
    Prestec find(String idPrestec);

    @Query("SELECT * FROM Prestec WHERE idPrestec=:idPrestec")
    PrestecComplet findComplet(String idPrestec);

    @Query("UPDATE Prestec SET dataRetorn = :dataRetorn WHERE idPrestec = :idPrestec")
    int setRetornat(String idPrestec, String dataRetorn);

    @Insert
    void insert(Prestec prestec);

    @Delete
    void delete(Prestec prestec);
}
