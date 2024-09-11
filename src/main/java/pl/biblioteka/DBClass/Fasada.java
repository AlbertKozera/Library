package pl.biblioteka.DBClass;

import pl.biblioteka.Interfejsy.FasadaInterface;

public class Fasada implements FasadaInterface {
    private DodajCzytelnik dodajCzytelnik;
    private AktualneWypozyczeniaUs aktualneWypozyczeniaUs;
    private Egzemplarz egzemplarz;
    private Grafik grafik;
    private Kary kary;
    private Ksiazka ksiazka;
    private Osoba osoba;
    private Wypozyczenie wypozyczenie;
    private Zamowienia zamowienia;
    private Zwrot zwrot;

    public Fasada(){
        /*dodajCzytelnik = new DodajCzytelnik();
        aktualneWypozyczeniaUs = new AktualneWypozyczeniaUs();
        egzemplarz = new Egzemplarz();
        grafik = new Grafik();
        kary = new Kary();
        ksiazka = new Ksiazka();
        osoba = new Osoba();
        wypozyczenie = new Wypozyczenie();
        zamowienia = new Zamowienia();
        zwrot = new Zwrot();*/
    }
}
