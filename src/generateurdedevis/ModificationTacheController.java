/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generateurdedevis;

import classesmetiers.TacheComplete;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isoyturk
 */
public class ModificationTacheController implements Initializable {

    int cmptErreur;
    @FXML
    private TextField descTache;
    @FXML
    private TextField quantiteTache;
    @FXML
    private TextField unitéTache;
    @FXML
    private TextField prixTache;
    /*-------------attributs classe----------*/
    static EditerDevisController e;
    @FXML
    private Label erreurVide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        descTache.setText(EditerDevisController.modifierDescTache);
        quantiteTache.setText("" + EditerDevisController.modifierQuantiteTache);
        unitéTache.setText(EditerDevisController.modifierUniteTache);
        prixTache.setText("" + EditerDevisController.modifierPrixTache);
    }

    @FXML
    private void annuler(ActionEvent event) {
        Stage quit = (Stage) descTache.getScene().getWindow();
        quit.close();
    }

    @FXML
    private void appliquerModification(ActionEvent event) {
        if (descTache.getText().length() != 0
                && quantiteTache.getText().length() != 0 && EditerDevisController.isNumber(quantiteTache.getText())
                && unitéTache.getText().length() != 0 && prixTache.getText().length() != 0
                && EditerDevisController.isNumber(prixTache.getText())) {
            for (TacheComplete t : EditerDevisController.touteLesTaches.values()) {
                if (EditerDevisController.modifierNumTacheChanger == t.getNumTache()) {
                    t.setDescriptionTache(descTache.getText());
                    t.setQuantiteTache(Integer.valueOf(quantiteTache.getText()));
                    t.setUniteTache(unitéTache.getText());
                    t.setPrixTache(Integer.valueOf(prixTache.getText()));
                }
            }
            e.mettreAjourListeView();
            Stage quit = (Stage) descTache.getScene().getWindow();
            quit.close();
        } else {
            if (cmptErreur % 2 == 0) {
                erreurVide.setText("Il reste des champs vide !");
                erreurVide.setTextFill(Color.RED);
                erreurVide.setTextAlignment(TextAlignment.CENTER);
            } else if (cmptErreur % 2 == 1) {
                erreurVide.setText("Il reste des champs vide !");
                erreurVide.setTextFill(Color.BLUE);
                erreurVide.setTextAlignment(TextAlignment.CENTER);
            }
            cmptErreur++;
        }
    }

}
