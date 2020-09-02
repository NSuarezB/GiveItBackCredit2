package com.example.giveitback.src;

import androidx.room.Embedded;
import androidx.room.Relation;

public class PrestecComplet {
    @Embedded
    public Prestec prestec;
    @Relation(
            parentColumn = "idPersona",
            entityColumn = "idPersona"
    )
    public Persona persona;
    @Relation(
            parentColumn = "idObjecte",
            entityColumn = "idObjecte"
    )
    public Objecte objecte;
}
