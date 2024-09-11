package pl.biblioteka.DBClass;

import javafx.beans.property.*;

import java.time.LocalDate;


public class Wypozyczenie {



    private final IntegerProperty Nr_kartyBib;

    public Wypozyczenie( Integer Nr_kartyBib)
    {
        this.Nr_kartyBib = new SimpleIntegerProperty(Nr_kartyBib);
    }

    //Getters

    public int getNr_kartyBib() {
         return Nr_kartyBib.get();
    }


    //Setters

    public void setNr_kartyBib(Integer value) {
       Nr_kartyBib.set(value);
    }

    //Property

    public IntegerProperty Nr_kartyBibProperty() {
       return Nr_kartyBib;
    }
}

