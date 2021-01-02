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
public class LinerDevisController implements Initializable {

    int cmptErreur;
    public static String entete = "";
    @FXML
    private TextField formePiscine;
    @FXML
    private TextField longueurPiscine;
    @FXML
    private TextField largeurPiscine;
    @FXML
    private TextField typePiscine;
    @FXML
    private TextField profondeurPetitBain;
    @FXML
    private TextField profondeurGrandBain;
    @FXML
    private Label logueurXlargeur;
    @FXML
    private TextField infoTravaux;
    @FXML
    private Label erreurChampsVide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formePiscine.setPromptText("forme piscine");
        longueurPiscine.setPromptText("l");
        largeurPiscine.setPromptText("L");
        typePiscine.setPromptText("type piscine, desjoyaux ou tradi");
        profondeurPetitBain.setPromptText("p");
        profondeurGrandBain.setPromptText("g");
        infoTravaux.setPromptText("info travaux");
         logueurXlargeur.setText(longueurPiscine.getText() + " X " + largeurPiscine.getText());
    }

    @FXML
    private void validerEntete(ActionEvent event) {
        if (formePiscine.getText().length() != 0 && longueurPiscine.getText().length() != 0
                && largeurPiscine.getText().length() != 0 && typePiscine.getText().length() != 0
                && infoTravaux.getText().length() != 0 && profondeurGrandBain.getText().length()
                != 0 && profondeurPetitBain.getText().length() != 0) {
            entete = "BASSINE " + formePiscine.getText().toUpperCase() + ", "
                    + longueurPiscine.getText().toUpperCase() + " X " + largeurPiscine.getText().toUpperCase()
                    + ", " + typePiscine.getText().toUpperCase() + "\n"
                    + "CHANGEMENT DE LINER " + infoTravaux.getText().toUpperCase() + "\n"
                    + "FOURNITURES POUR PISCINE ( " + logueurXlargeur.getText().toUpperCase()
                    + " X " + profondeurPetitBain.getText().toUpperCase() + " X "
                    + profondeurGrandBain.getText().toUpperCase() + " ) ETANCHEITE LINER";
            chargerFenetre("EditerDevis.fxml", "Devis de Liner");
        } else {
            if (cmptErreur % 2 == 0) {
                erreurChampsVide.setText("Il reste des champs vide !");
                erreurChampsVide.setTextFill(Color.RED);
                erreurChampsVide.setTextAlignment(TextAlignment.CENTER);
            } else if (cmptErreur % 2 == 1) {
                erreurChampsVide.setText("Il reste des champs vide !");
                erreurChampsVide.setTextFill(Color.BLUE);
                erreurChampsVide.setTextAlignment(TextAlignment.CENTER);
            }
            cmptErreur++;
        }
    }

    @FXML
    private void annuler(ActionEvent event) {
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
            Stage menu = (Stage) longueurPiscine.getScene().getWindow();
            menu.close();
        } catch (IOException e) {
            System.out.println("Chargement fenêtre impossible !");
        }
    }

    @FXML
    private void texteChange(ActionEvent event) {
        logueurXlargeur.setText(longueurPiscine.getText() + " X " + largeurPiscine.getText());
    }
}
