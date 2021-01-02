/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generateurdedevis;

import static generateurdedevis.LinerDevisController.entete;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ilker
 */
public class VoletDevisController implements Initializable {

    public static String entete;
    int cmptErreur;
    @FXML
    private TextField longueurVolet;
    @FXML
    private TextField largeurVolet;
    @FXML
    private TextField formeVolet;
    @FXML
    private TextField typeVolet;
    @FXML
    private Label longueurLabel;
    @FXML
    private Label largeurLabel;
    @FXML
    private TextField typeVoletPlusMarque;
    @FXML
    private Label erreurChamps;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        longueurVolet.setPromptText("l");
        largeurVolet.setPromptText("L");
        formeVolet.setPromptText("forme volet");
        typeVolet.setPromptText("type volet");
        typeVoletPlusMarque.setPromptText("type volet, marque");
        longueurLabel.setText(longueurVolet.getText());
        largeurLabel.setText(largeurVolet.getText());
    }

    @FXML
    private void validerBoutton(ActionEvent event) {
        if (longueurVolet.getText().length() != 0 && largeurVolet.getText().length() != 0
                && formeVolet.getText().length() != 0 && typeVolet.getText().length() != 0
                && typeVoletPlusMarque.getText().length() != 0) {
            entete = "BASSINE " + longueurVolet.getText().toUpperCase() + " X " + largeurVolet.getText().toUpperCase() + ", "
                    + formeVolet.getText().toUpperCase() + "\n"
                    + "VOLET DE SÉCURITÉ " + typeVoletPlusMarque.getText().toUpperCase() + "\n"
                    + "FOURNITURES ET INSTALLATION POUR UNE PISCINE ( " + longueurVolet.getText().toUpperCase() + " X "
                    + largeurVolet.getText().toUpperCase() + "), " + typeVolet.getText().toUpperCase() + ", ELECTRIQUE";
            chargerFenetre("EditerDevis.fxml", "Devis de Liner");
        } else {
            if (cmptErreur % 2 == 0) {
                erreurChamps.setText("Il reste des champs vide !");
                erreurChamps.setTextFill(Color.RED);
                erreurChamps.setTextAlignment(TextAlignment.CENTER);
            } else if (cmptErreur % 2 == 1) {
                erreurChamps.setText("Il reste des champs vide !");
                erreurChamps.setTextFill(Color.BLUE);
                erreurChamps.setTextAlignment(TextAlignment.CENTER);
            }
            cmptErreur++;
        }
    }

    @FXML
    private void annulerBoutton(ActionEvent event) {
        chargerFenetre("InfosSurClient.fxml", "Informations sur le client");
    }

    void chargerFenetre(String nomFichier, String titreScene) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(nomFichier));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(titreScene);
            stage.setScene(new Scene(root1));
            stage.show();
            Stage menu = (Stage) erreurChamps.getScene().getWindow();
            menu.close();
        } catch (IOException e) {
            System.out.println("Chargement fenêtre impossible !");
        }
    }

    @FXML
    private void texteChange(ActionEvent event) {
        longueurLabel.setText(longueurVolet.getText());
        largeurLabel.setText(largeurVolet.getText());
    }

}
