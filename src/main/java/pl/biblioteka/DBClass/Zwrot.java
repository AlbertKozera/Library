package pl.biblioteka.DBClass;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Zwrot {

    private final IntegerProperty idZwrot;
    private final ObjectProperty<LocalDate> Data_zwrotu;
    private final StringProperty Kara;
    private final IntegerProperty Czytelnik_idCzytelnik;
    private final IntegerProperty Egzemplarz_idEgzemplarz;

    public Zwrot (Integer idZwrot, LocalDate Data_zwrotu, String Kara, Integer Czytelnik_idCzytelnik, Integer Egzemplarz_idEgzemplarz){
        this.idZwrot = new SimpleIntegerProperty(idZwrot);
        this.Data_zwrotu = new SimpleObjectProperty<LocalDate>(Data_zwrotu);
        this.Kara = new SimpleStringProperty(Kara);
        this.Czytelnik_idCzytelnik = new SimpleIntegerProperty(Czytelnik_idCzytelnik);
        this.Egzemplarz_idEgzemplarz = new SimpleIntegerProperty(Egzemplarz_idEgzemplarz);
    }

    public int getIdZwrot() {
        return idZwrot.get();
    }

    public IntegerProperty idZwrotProperty() {
        return idZwrot;
    }

    public void setIdZwrot(int idZwrot) {
        this.idZwrot.set(idZwrot);
    }

    public LocalDate getData_zwrotu() {
        return Data_zwrotu.get();
    }

    public ObjectProperty<LocalDate> data_zwrotuProperty() {
        return Data_zwrotu;
    }

    public void setData_zwrotu(LocalDate data_zwrotu) {
        this.Data_zwrotu.set(data_zwrotu);
    }

    public String getKara() {
        return Kara.get();
    }

    public StringProperty karaProperty() {
        return Kara;
    }

    public void setKara(String kara) {
        this.Kara.set(kara);
    }

    public int getCzytelnik_idCzytelnik() {
        return Czytelnik_idCzytelnik.get();
    }

    public IntegerProperty czytelnik_idCzytelnikProperty() {
        return Czytelnik_idCzytelnik;
    }

    public void setCzytelnik_idCzytelnik(int czytelnik_idCzytelnik) {
        this.Czytelnik_idCzytelnik.set(czytelnik_idCzytelnik);
    }

    public int getEgzemplarz_idEgzemplarz() {
        return Egzemplarz_idEgzemplarz.get();
    }

    public IntegerProperty egzemplarz_idEgzemplarzProperty() {
        return Egzemplarz_idEgzemplarz;
    }

    public void setEgzemplarz_idEgzemplarz(int egzemplarz_idEgzemplarz) {
        this.Egzemplarz_idEgzemplarz.set(egzemplarz_idEgzemplarz);
    }
}
