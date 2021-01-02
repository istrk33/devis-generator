/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generateurdedevis;

import classesmetiers.Devis;
import classesmetiers.TacheComplete;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ilker
 */
public class EditerDevisController implements Initializable {

    @FXML
    private TextField descriptionTache;
    @FXML
    private TextField quantiteTache;
    @FXML
    private TextField uniteTache;
    @FXML
    private TextField prixTache;
    @FXML
    private TextField titrePartie;
    @FXML
    private ListView<TacheComplete> listeVueDesTaches;

    public static HashMap<Integer, TacheComplete> touteLesTaches = new HashMap<>();
    int idTacheAModifier;
   

    /*POUR MODIFICATION*/
    static int modifierNumTacheChanger;
    static String modifierDescTache;
    static int modifierQuantiteTache;
    static String modifierUniteTache;
    static int modifierPrixTache;
    static TacheComplete selectionneAModifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void supprimerTache(ActionEvent event) {
        if (listeVueDesTaches.getSelectionModel().getSelectedItem() != null) {
            int id = listeVueDesTaches.getSelectionModel().getSelectedItem().getNumTache();
            touteLesTaches.remove(id);
            mettreAjourListeView();
        }
    }

    @FXML
    private void modifierTache(ActionEvent event) {
        if (listeVueDesTaches.getSelectionModel().getSelectedItem() != null) {
            modifierNumTacheChanger=listeVueDesTaches.getSelectionModel().getSelectedItem().getNumTache();
            modifierDescTache=listeVueDesTaches.getSelectionModel().getSelectedItem().getDescriptionTache();
            modifierQuantiteTache=listeVueDesTaches.getSelectionModel().getSelectedItem().getQuantiteTache();
            modifierUniteTache=listeVueDesTaches.getSelectionModel().getSelectedItem().getUniteTache();
            modifierPrixTache=listeVueDesTaches.getSelectionModel().getSelectedItem().getPrixTache();
            ModificationTacheController.e=this;
            chargerFenetre("ModificationTache.fxml","Modification de la tâche sélectionné");
        }
    }

    @FXML
    private void genererDevis(ActionEvent event) {
        Devis d=new Devis(InfosSurClientController.titreDevis, InfosSurClientController.nomPrenom, InfosSurClientController.adresse, InfosSurClientController.villeCodePostal);
        d.genererDevis();
    }

    @FXML
    private void sauvegarderDevis(ActionEvent event) {
    }

    @FXML
    private void quitter(ActionEvent event) {
        Stage quit = (Stage) uniteTache.getScene().getWindow();
        quit.close();
    }

    @FXML
    private void ajouterTache(ActionEvent event) {
        if (descriptionTache.getText().length() != 0
                && quantiteTache.getText().length() != 0 && isNumber(quantiteTache.getText())
                && uniteTache.getText().length() != 0 && prixTache.getText().length() != 0
                && isNumber(prixTache.getText())) {
            TacheComplete nouvelleTache = new TacheComplete(descriptionTache.getText(), Integer.valueOf(quantiteTache.getText()), uniteTache.getText(), Integer.valueOf(prixTache.getText()), false);
            touteLesTaches.put(nouvelleTache.getNumTache(), nouvelleTache);
            mettreAjourListeView();
        }
    }
    
    @FXML
    private void ajouterTitre(ActionEvent event) {
        if (titrePartie.getText().length() != 0) {
            TacheComplete nouveauTitre = new TacheComplete(titrePartie.getText().toUpperCase(), true);
            touteLesTaches.put(nouveauTitre.getNumTache(), nouveauTitre);
            mettreAjourListeView();
        }
    }

     public void mettreAjourListeView() {
        listeVueDesTaches.getItems().clear();
        for (TacheComplete t : touteLesTaches.values()) {
            listeVueDesTaches.getItems().add(t);
        }
    }

    static boolean isNumber(String str) {
        return str.matches("[0-9]+");
    }

    public void chargerFenetre(String nomFichier,String titre) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(nomFichier));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Modifier la Tache");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("Chargement fenêtre impossible !");
        }
    }

}
