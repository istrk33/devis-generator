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
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isoyturk
 */
public class InfosSurClientController implements Initializable {

    static String enteteLiner;
    static String enteteVolet;
    static String titreDevis;
    static String nomPrenom;
    static String adresse;
    static String villeCodePostal;
    public static String modeDevis;
    int cmptErreur = 0;

    @FXML
    private Button annulerButton;
    @FXML
    private Label messageDerreur;
    @FXML
    private TextField textTitreDevis;
    @FXML
    private TextField textNomPrenom;
    @FXML
    private TextField textCPVille;
    @FXML
    private TextField textAdresse;
    @FXML
    private RadioButton poseVolet;
    @FXML
    private RadioButton poseLiner;
    @FXML
    private RadioButton autre;
    @FXML
    private ToggleGroup typeDevis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textTitreDevis.setPromptText("titre");
        textNomPrenom.setPromptText("nom et prénom");
        textAdresse.setPromptText("adresse");
        textCPVille.setPromptText("ville");
    }

    @FXML
    private void annulerAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Informations sur le client");
            stage.setScene(new Scene(root1));
            stage.show();
            stage.setResizable(false);
            Stage infoClients = (Stage) annulerButton.getScene().getWindow();
            infoClients.close();
        } catch (IOException e) {
            System.out.println("Chargement de la fenêtre impossible !");
        }
    }

    @FXML
    private void confirmerInfos(ActionEvent event) {
        if (textAdresse.getText().length() != 0 && textTitreDevis.getText().length() != 0
                && textCPVille.getText().length() != 0 && textNomPrenom.getText().length() != 0 && typeDevis.getSelectedToggle() != null) {
            adresse = textAdresse.getText();
            nomPrenom = textNomPrenom.getText();
            villeCodePostal = textCPVille.getText();
            titreDevis = textTitreDevis.getText();
            if (typeDevis.getSelectedToggle() == poseVolet) {
                chargerFenetre("VoletDevis.fxml", "Entête pour volet");
                modeDevis="volet";
            } else if (typeDevis.getSelectedToggle() == poseLiner) {
                chargerFenetre("LinerDevis.fxml", "Entête pour liner");
                modeDevis="liner";
            } else if (typeDevis.getSelectedToggle() == autre) {
                chargerFenetre("EditerDevis.fxml", "Edition de devis");
                modeDevis="autre";
            }
        } else {
            if (cmptErreur % 2 == 0) {
                messageDerreur.setText("Il reste des champs vide !");
                messageDerreur.setTextFill(Color.RED);
                messageDerreur.setTextAlignment(TextAlignment.CENTER);
            } else if (cmptErreur % 2 == 1) {
                messageDerreur.setText("Il reste des champs vide !");
                messageDerreur.setTextFill(Color.BLUE);
                messageDerreur.setTextAlignment(TextAlignment.CENTER);
            }
            cmptErreur++;
        }
    }

void chargerFenetre(String nomFichier, String titreScene) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(nomFichier));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(titreScene);
            stage.setScene(new Scene(root1));
            stage.show();
            Stage menu = (Stage) annulerButton.getScene().getWindow();
            menu.close();
        } catch (IOException e) {
            System.out.println("Chargement fenêtre impossible !");
        }
    }
}
