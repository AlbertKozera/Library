package pl.biblioteka.DBClass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Czytelnik {


    private final IntegerProperty idCzytelnik;
    private final IntegerProperty Osoba_idOsoba;
    private final StringProperty Nr_kartyBib;

    public Czytelnik (Integer idCzytelnik, Integer Osoba_idOsoba, String Nr_kartyBib) //Konstruktor
    {
        this.idCzytelnik = new SimpleIntegerProperty(idCzytelnik);
        this.Osoba_idOsoba = new SimpleIntegerProperty(Osoba_idOsoba);
        this.Nr_kartyBib = new SimpleStringProperty(Nr_kartyBib);
    }
 //Gettery settery
    public int getIdCzytelnik() {
        return idCzytelnik.get();
    }

    public IntegerProperty idCzytelnikProperty() {
        return idCzytelnik;
    }

    public void setIdCzytelnik(int idCzytelnik) {
        this.idCzytelnik.set(idCzytelnik);
    }

    public int getOsoba_idOsoba() {
        return Osoba_idOsoba.get();
    }

    public IntegerProperty osoba_idOsobaProperty() {
        return Osoba_idOsoba;
    }

    public void setOsoba_idOsoba(int osoba_idOsoba) {
        this.Osoba_idOsoba.set(osoba_idOsoba);
    }

    public String getNr_kartyBib() {
        return Nr_kartyBib.get();
    }

    public StringProperty nr_kartyBibProperty() {
        return Nr_kartyBib;
    }

    public void setNr_kartyBib(String nr_kartyBib) {
        this.Nr_kartyBib.set(nr_kartyBib);
    }
}
