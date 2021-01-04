/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesmetiers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import generateurdedevis.EditerDevisController;
import generateurdedevis.InfosSurClientController;
import generateurdedevis.LinerDevisController;
import generateurdedevis.VoletDevisController;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author isoyturk
 */
public class Devis {

    private final String titreDevis;
    private final String nomPrenom;
    private final String adresse;
    private final String codePostalVille;

    public Devis(String titreDevis, String nomPrenom, String adresse, String codePostalVille) {
        this.titreDevis = titreDevis;
        this.nomPrenom = nomPrenom;
        this.adresse = adresse;
        this.codePostalVille = codePostalVille;
    }

    public void genererDevis() {
        Document document = new Document();
        Font bold = FontFactory.getFont(FontFactory.COURIER_BOLD, 18);
        Font little = FontFactory.getFont(FontFactory.COURIER, 11);
        Font signature = FontFactory.getFont(FontFactory.TIMES_BOLD, 15);
        try {
            String path = System.getProperty("user.home") + "\\Desktop\\devis";
            System.out.println(path);
            new File(path).mkdirs();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(path + titreDevis + ".pdf", false));
            document.open();
            document.add(new Paragraph("Parap", bold));
            PdfPTable table = new PdfPTable(2); // 2 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            //Set Column widths
            float[] columnWidths = {1f, 1f};
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Paragraph("Monsieur x y\n"
                    + "informations entreprise\n"
                    + "informations entreprise\n"
                    + "\n"
                    + "adresse\n"
                    + "code postal ville\n"
                    + "Tel: Fax: 06 06 06 06 06\n"
                    + "     Prt: 06 06 06 06 06", little));
            cell1.setBorderColor(BaseColor.BLACK);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();

            PdfPCell cell2 = new PdfPCell(new Paragraph("Mme. , M. : " + nomPrenom + "\n"
                    + adresse + "\n"
                    + codePostalVille + "\n"
                    + "Pessac, le " + dateFormat.format(date), little));
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);

            table.addCell(cell1);
            table.addCell(cell2);

            document.add(table);
            Paragraph des=new Paragraph("DESIGNATION D E V I S \n", bold);
            des.setAlignment(Element.ALIGN_CENTER);
            document.add(des);
            if (InfosSurClientController.modeDevis.equals("volet")) {
                document.add(new Paragraph(VoletDevisController.entete));
            } else if (InfosSurClientController.modeDevis.equals("liner")) {
                document.add(new Paragraph(LinerDevisController.entete));
            }
            document.add(new Paragraph("Comme convenu, après mon passage à votre domicile, je vous prie de bien vouloir trouver ci-dessous le devis pour vos travaux.\n"
                    + "\n", little));

            /*PARTIE 2 DU DEVIS*/
           

            for (TacheComplete t : EditerDevisController.touteLesTaches.values()) {
                 PdfPTable tablee = new PdfPTable(new float[]{3, 1});
            tablee.setWidthPercentage(100); //Width 100%
                if (t.isEstUnTitre()) {
                    document.add(tablee);
                    System.out.println(t.toStringTitre());
                    Paragraph p = new Paragraph( t.toStringTitre() + "\n"
                            + "\n", little);
                    p.setAlignment(Element.ALIGN_CENTER);
                    document.add(p);
                } else {
                    System.out.println(t.toString());
                    
                    PdfPCell cell3 = new PdfPCell();
                    cell3.addElement(new Paragraph(t.getDescriptionTache() + " " + t.getQuantiteTache() + " " + t.getUniteTache(), little));
                    cell3.setBorderColor(BaseColor.BLACK);
                    cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
                    tablee.addCell(cell3);

                    PdfPCell cell4 = new PdfPCell();
                    cell4.addElement(new Paragraph(t.getPrixTache() + " €", little));
                    cell4.setBorderColor(BaseColor.BLACK);
                    cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tablee.addCell(cell4); 
                    document.add(tablee);
                }
            }
            

            /*FIN DES TACHES*/
            document.add(new Paragraph("Condition de paiement"
                    + "\n"
                    + "\n", bold));
            PdfPTable tableee = new PdfPTable(new float[]{1, 1});
            tableee.setWidthPercentage(100); //Width 100%

            PdfPCell cell5 = new PdfPCell(new Paragraph("35% à la commande\n"
                    + "25% au début des travaux\n"
                    + "30% à la moitié des travaux\n"
                    + "Solde à la fin des travaux", little));
            cell5.setBorderColor(BaseColor.BLACK);
            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell cell6 = new PdfPCell(new Paragraph("Montant total HT " + (calculerSommeTTC() - (calculerSommeTTC() / 100.0) * 20.0) + "€\n"
                    + "TVA: 20% " + ((calculerSommeTTC() / 100.0) * 20.0) + " €\n"
                    + "Montant total TTC " + /*(calculerSommeHT() + (calculerSommeHT() / 100.0) * 20.0)*/calculerSommeTTC()+ " €", little));
            cell6.setBorderColor(BaseColor.BLACK);
            cell6.setHorizontalAlignment(Element.ALIGN_RIGHT);

            tableee.addCell(cell5);
            tableee.addCell(cell6);
            document.add(tableee);

            document.add(new Paragraph("Si cette étude vous convient, pour "
                    + "passer votre commande veuillez nous retourner un "
                    + "exemplaire signé avec la mention manuscrite "
                    + "( \" bon pour accord\" ) et un chèque d'acompte 35%"
                    + " à l'ordre de :", little));
            document.add(new Paragraph("entreprise de m. x", bold));
            document.add(new Paragraph("Mention \"bon pour accord\" \n"
                    + "SIGNATURE\n "
                    + "\n", signature));

            document.add(new Paragraph("Dans l'attente de vos nouvelles, je vous"
                    + " prie de recevoir, Madame, Monsieur, nos sincères salutations.\n"
                    + "Si tout problèmes non aperçu dès le départ se remarque"
                    + "par la suite, nous établirons, un devis supplémentaire.", little));
            document.add(new Paragraph("Devis valable pendant 30 jours", bold));

            document.close();
            writer.close();
            //ifPdfGenerated.setText("Devis généré dans le dossier data");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int calculerSommeTTC() {
        int somme = 0;
        for (TacheComplete t : EditerDevisController.touteLesTaches.values()) {
            if (!t.isEstUnTitre()) {
                somme += t.getPrixTache();
            }
        }
        return somme;
    }
}
