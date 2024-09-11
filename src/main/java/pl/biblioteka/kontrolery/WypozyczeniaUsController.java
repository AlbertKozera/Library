package pl.biblioteka.kontrolery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import pl.biblioteka.ConnectionDB;
import pl.biblioteka.DBClass.AktualneWypozyczeniaUs;
import pl.biblioteka.DBClass.Ksiazka;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WypozyczeniaUsController {

    private MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    @FXML
    private TextField numerKarty;
    @FXML
    private TableView<AktualneWypozyczeniaUs> TableWypozyczenia;
    @FXML
    private TableColumn<AktualneWypozyczeniaUs,String> autorCol;
    @FXML
    private TableColumn<AktualneWypozyczeniaUs,String> tytulCol;
    @FXML
    private TableColumn<AktualneWypozyczeniaUs,String> wydawnictwoCol;
    @FXML
    private TableColumn<AktualneWypozyczeniaUs,String> nrEgzCol;

    private ObservableList<AktualneWypozyczeniaUs> wypozyczenia;

    ConnectionDB connectionDB=new ConnectionDB(); //tworzenie połączenia z bazą
    Connection connection=connectionDB.getConnection();

    @FXML
    public void powrotUs() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/User.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserController userController = loader.getController();
        userController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public void odswiezWypUs() throws SQLException {
        wypozyczenia= FXCollections.observableArrayList();
        String query = "SELECT idCzytelnik FROM czytelnik WHERE Nr_kartyBib = '"+numerKarty.getText()+"'";
        PreparedStatement preparedStatement=connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        while(resultSet.next()){
            int idCzytelnik = resultSet.getInt(1);
            String query2 = "SELECT Egzemplarz_idEgzemplarz FROM wypozyczenie WHERE Czytelnik_idCzytelnik = '"+idCzytelnik+"'";
            PreparedStatement preparedStatement2=connection.prepareStatement(query2);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            while (resultSet2.next()){
                int idEgzemplarz = resultSet2.getInt(1);
                String query3 = "SELECT idEgzemplarz FROM egzemplarz WHERE idEgzemplarz = '"+idEgzemplarz+"'";
                PreparedStatement preparedStatement3=connection.prepareStatement(query3);
                ResultSet resultSet3 = preparedStatement3.executeQuery();
                while (resultSet3.next()){
                    System.out.println("1");
                    int idEgzemplarz2 = resultSet3.getInt(1);
                    String query4 = "SELECT Ksiazka_idKsiazka FROM egzemplarz,ksiazka WHERE Ksiazka_idKsiazka = idKsiazka";
                    PreparedStatement preparedStatement4=connection.prepareStatement(query4);
                    ResultSet resultSet4 = preparedStatement4.executeQuery();
                    while (resultSet4.next()){
                        int Ksiazka_idKsiazka = resultSet4.getInt(1);
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT Autor,Tytul,Wydawnictwo,Numer FROM ksiazka,egzemplarz WHERE '"+idEgzemplarz2+"' = '"+Ksiazka_idKsiazka+"';");
            while (rs.next()){
                wypozyczenia.add(new AktualneWypozyczeniaUs(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }

                        autorCol.setCellValueFactory(new PropertyValueFactory<>("Autor"));
                        tytulCol.setCellValueFactory(new PropertyValueFactory<>("Tytul"));
                        wydawnictwoCol.setCellValueFactory(new PropertyValueFactory<>("Wydawnictwo"));
                        nrEgzCol.setCellValueFactory(new PropertyValueFactory<>("NrEgzemplarza"));
                        TableWypozyczenia.setItems(wypozyczenia);
                    }
                }
            }
        }
    }
}
