package pl.biblioteka.kontrolery;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ProfilUsController {

    private MainController mainController;

    public void setMainController(MainController mainController) {

        this.mainController = mainController;
    }

    @FXML
    public void aktualizujProfilUs() {
    }

    @FXML
    public void odswiezDaneUs() {
    }

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
}
