package com.example.giveitback.src;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Objecte.class, parentColumns = "idObjecte", childColumns = "idObjecte"),
        @ForeignKey(entity = Persona.class, parentColumns = "idPersona", childColumns = "idPersona")
})
public class Prestec {

    @PrimaryKey
    @NonNull
    public String idPrestec;

    @ColumnInfo(name = "idPersona")
    @NonNull
    public String idPersona;

    @ColumnInfo(name = "idObjecte")
    @NonNull
    public String idObjecte;

    @ColumnInfo(name = "dataRetorn")
    public String dataRetorn;

    @ColumnInfo(name = "dataPrestec")
    public String dataPrestec;

//    @ColumnInfo(name = "notaAddicional")
//    public String notaAddicional;


}
