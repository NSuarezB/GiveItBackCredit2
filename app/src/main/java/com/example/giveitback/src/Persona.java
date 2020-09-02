package com.example.giveitback.src;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Persona {
    @PrimaryKey
    @NonNull
    public String idPersona;

    @ColumnInfo(name = "nom")
    public String nom;

    @ColumnInfo(name = "cognom")
    public String cognom;

    @ColumnInfo(name = "numTelefon")
    public String numTelefon;

    @ColumnInfo(name = "correuElectronic")
    public String correuElectronic;

    @ColumnInfo(name = "img")
    public String img;


    @Override
    public String toString() {
        return nom;
    }


}
