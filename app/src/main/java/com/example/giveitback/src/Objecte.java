package com.example.giveitback.src;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Objecte {


    @PrimaryKey
    @NonNull
    public String idObjecte;

    @ColumnInfo(name = "nom")
    public String nom;

    @ColumnInfo(name = "definicio")
    public String definicio;

    @ColumnInfo(name = "img")
    public String img;

    @Override
    public String toString() {
        return nom;
    }
}
