package pl.biblioteka.kontrolery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import pl.biblioteka.ConnectionDB;
import pl.biblioteka.DBClass.DodajCzytelnik;

import java.io.IOException;
import java.sql.*;

public class CzytelnicyBibController {

    private MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }
//Deklaracje textfield, tableview orac table column
    @FXML
    private TextField idOsoby;
    @FXML
    private TextField imieUs;
    @FXML
    private TextField nazwiskoUs;
    @FXML
    private TextField telefonUs;
    @FXML
    private TextField LoginUs;
    @FXML
    private TextField hasloUs;
    @FXML
    private TextField mailUs;
    @FXML
    private TextField numerKartyUs;
    @FXML
    private TableView<DodajCzytelnik> TableAddUser;
    @FXML
    private TableColumn<DodajCzytelnik, Integer> idOsobyCol;
    @FXML
    private TableColumn<DodajCzytelnik,String> imieUsCol;
    @FXML
    private TableColumn<DodajCzytelnik,String> nazwiskoUsCol;
    @FXML
    private TableColumn<DodajCzytelnik,String> telefonUsCol;
    @FXML
    private TableColumn<DodajCzytelnik,String> loginUsCol;
    @FXML
    private TableColumn<DodajCzytelnik,String> hasloUsCol;
    @FXML
    private TableColumn<DodajCzytelnik,String> mailUsCol;
    @FXML
    private TableColumn<DodajCzytelnik,String> kartaUsCol;

    private ObservableList<DodajCzytelnik> czytelnikInfo;
    ConnectionDB connectionDB=new ConnectionDB();  // łączenie z bazą danych
    Connection connection=connectionDB.getConnection();

    public void executeQuery(String query) {
        Connection connection=connectionDB.getConnection();
        Statement st;
        try {
            st=connection.createStatement();
            st.executeUpdate(query);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void doadajUs() {  //dodawanie czytelników
        String insertOsoba = "insert into osoba values(null,?,?,?,?,?,?,?)";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(insertOsoba);
            preparedStatement.setString(1, imieUs.getText());
            preparedStatement.setString(2, nazwiskoUs.getText());
            preparedStatement.setString(3, loginUsCol.getText());
            preparedStatement.setString(4, hasloUs.getText());
            preparedStatement.setString(5, mailUs.getText());
            preparedStatement.setString(6, telefonUs.getText());
            preparedStatement.setString(7,"Czytelnik");
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        wyswietlUs();

    }

    @FXML
    public void szukajUs() {
    }

    @FXML
    public void powrotBib() {  //powrót do poprzedniego ekranu
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/Bibliotekarz.fxml"));
        Pane pane=null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BibliotekarzController bibliotekarzController = loader.getController();
        bibliotekarzController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    public void aktualizujUs() { //aktualizowanie danych w bazie danych
        String update = "UPDATE osoba SET Imie=?,Nazwisko=?,login=?,haslo=?,email=?, telefon=? where idOsoba = '"+idOsoby.getText()+"'";
        String updateKarta = "UPDATE czytelnik SET  Nr_kartyBib=? where Osoba_idOsoba = '"+idOsoby.getText()+"'";
        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, imieUs.getText());   //pobieranie danych z textfield i wstawianie ich do bazy danych
            preparedStatement.setString(2, nazwiskoUs.getText());
            preparedStatement.setString(3, LoginUs.getText());
            preparedStatement.setString(4, hasloUs.getText());
            preparedStatement.setString(5, mailUs.getText());
            preparedStatement.setString(6, telefonUs.getText());
            PreparedStatement preparedStatement1 = connection.prepareStatement(updateKarta);
            preparedStatement1.setString(1,numerKartyUs.getText());
            preparedStatement.execute();
            preparedStatement1.execute();
            preparedStatement.close();
            preparedStatement1.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /*public void usunUs() {
        String query = "DELETE FROM osoba WHERE idOsoba=?";
        String query2 = "DELETE FROM czytelnik WHERE Nr_kartyBib=?";

        ConnectionDB connectionDB=new ConnectionDB();
        Connection connection=connectionDB.getConnection();
        try
        {
            DodajCzytelnik dodajCzytelnik=(DodajCzytelnik) TableAddUser.getSelectionModel().getSelectedItem();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, dodajCzytelnik.getIdOsoba());
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
            preparedStatement.setString(1, dodajCzytelnik.getNr_kartyBib());
            preparedStatement2.executeUpdate();
            wyswietlUs();
            preparedStatement.close();
            preparedStatement2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


    public void wyswietlUs() {  //wyświetlanie danych w tabeli
        czytelnikInfo= FXCollections.observableArrayList();
        try {
            ResultSet rs=connection.createStatement().executeQuery("SELECT idOsoba,Imie,Nazwisko,login,haslo,email,telefon,Nr_kartyBib FROM osoba,czytelnik ;");
            while (rs.next()){
                czytelnikInfo.add(new DodajCzytelnik(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (SQLException e) {
            System.out.println("Blad" +e);
        }
        idOsobyCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, Integer>("idOsoba"));  //wyświetlenie danych w odpowiedniej kolumnie
        imieUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Imie"));
        nazwiskoUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Nazwisko"));
        loginUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Login"));
        hasloUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Haslo"));
        mailUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("email"));
        telefonUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Telefon"));
        kartaUsCol.setCellValueFactory(new PropertyValueFactory<DodajCzytelnik, String>("Nr_kartyBib"));

        TableAddUser.setItems(czytelnikInfo);
    }

    public void OnMauseClick() {  //funkcja która po kliknięciu w wiersz tabeli wyświetla dane w textfield
        try
        {
            DodajCzytelnik dodajCzytelnik=(DodajCzytelnik) TableAddUser.getSelectionModel().getSelectedItem();
            String query = "SELECT * FROM osoba,czytelnik";
            ConnectionDB connectionDB=new ConnectionDB();
            Connection connection=connectionDB.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            idOsoby.setText(String.valueOf(dodajCzytelnik.getIdOsoba()));
            imieUs.setText(String.valueOf(dodajCzytelnik.getImie()));
            nazwiskoUs.setText(dodajCzytelnik.getNazwisko());
            telefonUs.setText(dodajCzytelnik.getTelefon());
            LoginUs.setText(dodajCzytelnik.getLogin());
            hasloUs.setText(dodajCzytelnik.getHaslo());
            mailUs.setText(dodajCzytelnik.getEmail());
            numerKartyUs.setText(dodajCzytelnik.getNr_kartyBib());
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
