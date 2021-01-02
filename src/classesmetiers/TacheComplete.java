/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesmetiers;

/**
 *
 * @author ilker
 */
public class TacheComplete {

    private String titre;
    private int numTache;
    private String descriptionTache;
    private int quantiteTache;
    private String uniteTache;
    private int prixTache;
    private static int nbTache;
    private boolean estUnTitre;

    public TacheComplete(String descriptionTache, int quantiteTache, String uniteTache, int prixTache, boolean estUnTitre) {
        this.numTache = nbTache;
        this.descriptionTache = descriptionTache;
        this.quantiteTache = quantiteTache;
        this.uniteTache = uniteTache;
        this.prixTache = prixTache;
        this.nbTache++;
        this.estUnTitre = estUnTitre;
    }

    public TacheComplete(String titrePartie, boolean estUnTitre) {
        this.titre = titrePartie;
        this.numTache = nbTache;
        this.nbTache++;
        this.estUnTitre = estUnTitre;
    }

    @Override
    public String toString() {
        String aRetourner;
        if (estUnTitre) {
            aRetourner = numTache + " " + titre.toUpperCase();
        } else {
            aRetourner = numTache + " " + descriptionTache + "    " + quantiteTache + "    " + uniteTache + "   " + prixTache + " €";
        }
        return aRetourner;
    }

    public String toStringTitre() {
        return titre;
    }

    public int getNumTache() {
        return numTache;
    }

    public String getDescriptionTache() {
        return descriptionTache;
    }

    public int getQuantiteTache() {
        return quantiteTache;
    }

    public String getUniteTache() {
        return uniteTache;
    }

    public int getPrixTache() {
        return prixTache;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescriptionTache(String descriptionTache) {
        this.descriptionTache = descriptionTache;
    }

    public boolean isEstUnTitre() {
        return estUnTitre;
    }

    public void setQuantiteTache(int quantiteTache) {
        this.quantiteTache = quantiteTache;
    }

    public void setUniteTache(String uniteTache) {
        this.uniteTache = uniteTache;
    }

    public void setPrixTache(int prixTache) {
        this.prixTache = prixTache;
    }
}
