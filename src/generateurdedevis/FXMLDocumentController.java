/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generateurdedevis;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author isoyturk
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button bouttonQuitter;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void quitter(ActionEvent event) {
        Stage fermerMenu = (Stage) bouttonQuitter.getScene().getWindow();
        fermerMenu.close();
    }

    @FXML
    private void chargerDevis(ActionEvent event) {
    }

    @FXML
    private void nouveauDevis(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InfosSurClient.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Informations sur le client");
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setResizable(false);
            Stage menu = (Stage) bouttonQuitter.getScene().getWindow();
            menu.close();
        } catch (IOException e) {
            System.out.println("Chargement de la fenêtre impossible !");
        }
    }

}
