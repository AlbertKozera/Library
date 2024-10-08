package pl.biblioteka.kontrolery;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class WypozyczeniaBibController {

    private MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    @FXML
    public void wypozyczenie() {
    }

    @FXML
    public void zwrot() {
    }

    @FXML
    public void szukajKsiazke() {
    }

    @FXML
    public void powrotBib() {
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
}
