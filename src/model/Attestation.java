package model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import view.Creer;
import view.EditerEntreprise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Attestation {

    static float MARGIN = 50;
    static String breakingSPace = "\u00A0";
    PDDocument document = new PDDocument();

    public Attestation(Creer creer, EditerEntreprise editerEntreprise) {

        // Set propriétés du doc
        setDocumentProperties(document);

        // Ajout d'une page
        PDPage page = new PDPage(PDRectangle.LETTER);
        document.addPage(page);

        // Header partie gauche infos entreprise
        // TODO prévoir insert logo
        addHeader(document, page, editerEntreprise.getTxtNomEntreprise(), MARGIN, 750);
        addHeader(document, page, editerEntreprise.getTxtAdresseEntreprise(), MARGIN, 735);
        addHeader(document, page, editerEntreprise.getTxtCpEntreprise() + " " + editerEntreprise.getTxtVilleEntreprise(), MARGIN, 720);
        addHeader(document, page, editerEntreprise.getTxtTelEntreprise(), MARGIN, 705);
        addHeader(document, page, editerEntreprise.getTxtMailEntreprise(), MARGIN, 690);
        addHeader(document, page, editerEntreprise.getTxtAgrement(), MARGIN, 660);

        // Header droit import logo et infos client
        addImage(document, page, "src/media/logoArkadiaPc-transformed.jpeg", 420, 780); // logo
        addHeader(document, page, creer.getCmbTitre() + " " + creer.getTxtNomClient() + " " + creer.getTxtPrenomClient(), 420, 660);
        addHeader(document, page, creer.getTxtAdresseClient(), 420, 645);
        addHeader(document, page, creer.getTxtCPClient() + " " + creer.getTxtVilleClient(), 420, 630);
        addHeader(document, page, "le " + creer.getDateAttestation() + ",", 380, 605);

        // Titre
        addTitle(document, page, "Attestation destinée au Centre des Impôts", 150, 550);

        // Body
        String p1 = "       Je soussigné " + editerEntreprise.getCmbTitreGerant() + " " + editerEntreprise.getTxtPrenomGerant() + " " + editerEntreprise.getTxtNomGerant() + ", " +
                "gérant de l'organisme agréé " + editerEntreprise.getTxtNomEntreprise() + ", certifie que " + creer.getCmbTitre() + " " + creer.getTxtPrenomClient() + " " +
                creer.getTxtNomClient() + " a bénéficié d'assistance informatique à domicile, service à la personne :";
        String p2 = "               Montant total des factures pour l'année " + creer.getExerciceFiscal() + " : " + creer.getTxtMontantAttest() + "€";
        String p3 = "               Montant total payé en CESU préfinancé : 0 €";
        String p4 = "Intervenants : ";
        String p5 = "           "+editerEntreprise.getTxtPrenomGerant() + " " + editerEntreprise.getTxtNomGerant();
        String p6 = "Prestations : ";
        String p7 = "       Les sommes perçues pour financer les services à la personne sont à déduire de la valeur indiquée précédemment.";
        String p8 = "       La déclaration engage la responsabilité du seul contribuable";
        String p9 = "* Pour les personnes utilisant le Chèque Emploi Service Universel, seul le montant financé personnellement est déductible. " +
                "Une attestation est délivrée par les établissements préfinançant le CESU.";
        String p10 = "      Fait pour valoir ce que de droit,";
        String p11 = editerEntreprise.getTxtPrenomGerant() + " " + editerEntreprise.getTxtNomGerant() + ", gérant.";
        addParagraph(document, page, p1, MARGIN, 480);
        addParagraph(document, page, p2, MARGIN, 430);
        addParagraph(document, page, p3, MARGIN, 415);
        addParagraph(document, page, p4, MARGIN, 385);
        addParagraph(document, page, p5, MARGIN, 355);
        addParagraph(document, page, p6, MARGIN, 325);
        addParagraph(document, page, p7, MARGIN, 295);
        addParagraph(document, page, p8, MARGIN, 265);
        addParagraph(document, page, p9, MARGIN, 235);
        addParagraph(document, page, p10, MARGIN, 185);
        addParagraph(document, page, p11, MARGIN, 145);
        // signature gérant
        addImage(document, page, "src/media/signature.jpg", 300, 120); // signature
    }

    /**
     * Méthode d'ajout d'un titre
     *
     * @param document le document visé
     * @param page     la page visée
     * @param myTitle  le titre en String
     * @param x        position x largeur
     * @param y        position y hauteur
     */
    public void addTitle(PDDocument document, PDPage page, String myTitle, float x, float y) {
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 16);
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(myTitle);
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode d'ajout du header
     *
     * @param document le document visé
     * @param page     la page visée
     * @param myText   le texte en String
     * @param x        position x largeur
     * @param y        position y hauteur
     */
    public void addHeader(PDDocument document, PDPage page, String myText, float x, float y) {
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.beginText();
            if (myText.equals("Arkadia PC")) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
            } else {
                contentStream.setFont(PDType1Font.HELVETICA, 10);
            }
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(myText);
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode d'ajout d'un paragraphe
     *
     * @param document le document visé
     * @param page     la page visée
     * @param myText   le texte en String
     * @param x        position x largeur
     * @param y        position y hauteur
     */
    public void addParagraph(PDDocument document, PDPage page, String myText, float x, float y) {
        try {
            PDFont pdfFont = PDType1Font.HELVETICA;
            float fontSize = 11;
            float leading = 1.5f * fontSize;
            PDRectangle mediabox = page.getMediaBox();
            float margin = MARGIN/2;
            float width = mediabox.getWidth() - (2 * margin);
            float startX = mediabox.getLowerLeftX() + margin;
            float startY = mediabox.getUpperRightY() - margin;
            if (x > 0) {
                width = width - x;
                startX = startX + x;
            }
            if (y > 0) {
                startY = y;
            }
            // Division du paragraphe en plusieurs lignes, en fonction de la largeur et taille police
            ArrayList<String> lines = new ArrayList<>();
            int lastSpace = -1;
            while (myText.length() > 0) {
                int spaceIndex = myText.indexOf(' ', lastSpace + 1);
                if (spaceIndex < 0)
                    spaceIndex = myText.length();
                String subString = myText.substring(0, spaceIndex);
                float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
                if (size > width) {
                    if (lastSpace < 0)
                        lastSpace = spaceIndex;
                    subString = myText.substring(0, lastSpace);
                    lines.add(subString);
                    myText = myText.substring(lastSpace).trim();
                    lastSpace = -1;
                } else if (spaceIndex == myText.length()) {
                    lines.add(myText);
                    myText = "";
                } else {
                    lastSpace = spaceIndex;
                }
            }
            // Get sur le stream pour écrire les données
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.beginText();
            contentStream.setFont(pdfFont, fontSize);
            contentStream.newLineAtOffset(startX, startY);
            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -leading);
            }
            contentStream.endText();
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode ajout d'une image
     *
     * @param document  le document visé
     * @param page      la page visée
     * @param imageName le nom de l'image en String
     * @param x         position x largeur
     * @param y         position y hauteur
     */
    public void addImage(PDDocument document, PDPage page, String imageName, float x, float y) {
        try {
            // Get Content Stream for Writing Data
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            // Creating PDImageXObject object
            PDImageXObject pdImage = PDImageXObject.createFromFile(imageName, document);
            // Drawing the image in the PDF document
            if (imageName.equals("src/media/logoArkadiaPc-transformed.jpeg")) {
                contentStream.drawImage(pdImage, x, y - 80, 110, 80);
            } else {
                contentStream.drawImage(pdImage, x, y - 100, 200, 70);
            }
            // Closing the content stream
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Définition des meta propriétés
     *
     * @param document le document visé
     */
    public void setDocumentProperties(PDDocument document) {
        // TODO par défaut créateur, auteur, titre, sujet
        PDDocumentInformation docInformation = document.getDocumentInformation();
        docInformation.setAuthor("Araujo Adelino");
        docInformation.setTitle("Attestation destinée au Centre des Impôts");
        docInformation.setCreator("ArkadiaPC");
        docInformation.setSubject("Attestation fiscale");
        Calendar date = new GregorianCalendar();
        date.set(2022, 11, 28);
        docInformation.setCreationDate(date);
        /* docInformation.setModificationDate(date);
           docInformation.setKeywords("ArkadiaPC, attestation, fiscale"); */
    }

    /**
     * Méthode de sauvegarde du document
     * Initialisation de la source et nom fichier à sauvegarder
     */
    public void save() throws IOException {
        Creer creer = new Creer();
        String filePath = "C:\\Users\\Jean\\Documents\\AttestationsFiscalesTest\\";
        String fileName = "AttestationFiscale-" + creer.getExerciceFiscal() + "-" + creer.getTxtNomClient() +
                "-" + creer.getTxtPrenomClient() + ".pdf";
        document.save(filePath + fileName);
        System.out.println("Attestation sauvegardée. Chemin : " + filePath + fileName);
        document.close();
    }
}