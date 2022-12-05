package model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import view.CreateCertificate;
import view.EditCompany;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class Certificate {

    static float MARGIN = 50;
    private final PDDocument document = new PDDocument();


    public Certificate(CreateCertificate createCertificate, EditCompany editCompany) throws IOException {
        // Set propriétés du doc
        setDocumentProperties();
        // Ajout d'une page
        PDPage page = new PDPage(PDRectangle.LETTER);
        document.addPage(page);

        // Header partie gauche infos entreprise
        // TODO prévoir insert logo
        addHeader(document, page, editCompany.getTxtNomEntreprise(), MARGIN, 750);
        addHeader(document, page, editCompany.getTxtAdresseEntreprise(), MARGIN, 735);
        addHeader(document, page, editCompany.getTxtCpEntreprise() + " " + editCompany.getTxtVilleEntreprise(), MARGIN, 720);
        addHeader(document, page, editCompany.getTxtTelEntreprise(), MARGIN, 705);
        addHeader(document, page, editCompany.getTxtMailEntreprise(), MARGIN, 690);
        addHeader(document, page, editCompany.getTxtAgrement(), MARGIN, 660);

        // Header droit import logo et infos client
        addImage(document, page, "src/media/images/logoArkadiaPc-transformed.jpeg", 440, 780); // logo
        addHeader(document, page, createCertificate.getCustomerTitleCmb() + " " + createCertificate.getCustomerNameTxt() + " " + createCertificate.getCustomerFirstnameTxt(), 440, 660);
        addHeader(document, page, createCertificate.getCustomerAddressTxt(), 440, 645);
        addHeader(document, page, createCertificate.getCustomerZipTxt() + " " + createCertificate.getCustomerCityTxt(), 440, 630);
        addHeader(document, page, "le " + createCertificate.getDateAttestationFormat(), 440, 605);

        // Titre
        addTitle(document, page, "Certificate destinée au Centre des Impôts", 150, 550);

        // Body
        String p1 = "               Je soussigné " + editCompany.getCmbTitreGerant() + " " + editCompany.getTxtPrenomGerant() + " " + editCompany.getTxtNomGerant() + ", " +
                "gérant de l'organisme agréé " + editCompany.getTxtNomEntreprise() + ", certifie que " + createCertificate.getCustomerTitleCmb() + " " + createCertificate.getCustomerFirstnameTxt() + " " +
                createCertificate.getCustomerNameTxt() + " a bénéficié d'assistance informatique à domicile, service à la personne :";
        String p2 = "                       Montant total des factures pour l'année " + createCertificate.getFiscalYearTxt() + " : " + createCertificate.getCertificateAmountTxt() + "€";
        String p3 = "                       Montant total payé en CESU préfinancé* : 0 €";
        String p4 = "Intervenants : ";
        String p5 = "                       " + editCompany.getTxtPrenomGerant() + " " + editCompany.getTxtNomGerant();
        String p6 = "Prestations : ";
        String p7 = "               Les sommes perçues pour financer les services à la personne sont à déduire de la valeur indiquée précédemment.";
        String p8 = "               La déclaration engage la responsabilité du seul contribuable";
        String p9 = "* Pour les personnes utilisant le Chèque Emploi Service Universel, seul le montant financé personnellement est déductible. " +
                "Une attestation est délivrée par les établissements préfinançant le CESU.";
        String p10 = "              Fait pour valoir ce que de droit,";
        String p11 = editCompany.getTxtPrenomGerant() + " " + editCompany.getTxtNomGerant() + ", gérant.";
        addParagraph(document, page, p1, MARGIN, 480);
        addParagraph(document, page, p2, MARGIN, 430);
        addParagraph(document, page, p3, MARGIN, 415);
        addParagraph(document, page, p4, MARGIN, 385);
        addParagraph(document, page, p5, MARGIN, 355);
        addParagraph(document, page, p6, MARGIN, 325);
        addParagraph(document, page, p7, MARGIN, 295);
        addParagraph(document, page, p8, MARGIN, 255);
        addParagraph(document, page, p9, MARGIN, 225);
        addParagraph(document, page, p10, MARGIN, 155);
        addParagraph(document, page, p11, MARGIN, 115);
        // signature gérant
        addImage(document, page, "src/media/images/signature.jpg", 280, 150); // signature
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
    public void addTitle(PDDocument document, PDPage page, String myTitle, float x, float y) throws IOException {
        float yCordinate = page.getCropBox().getUpperRightY() - 245;
        float startX = page.getCropBox().getLowerLeftX() + 150;
        float endX = page.getCropBox().getUpperRightX() - 111;
        PDFont pdFont = PDType0Font.load(document, new File("src/media/font/Calibri Bold.TTF"));
        float fontSize = 20;
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.beginText();
            contentStream.setFont(pdFont, fontSize);
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(myTitle);
            contentStream.endText();
            contentStream.moveTo(startX, yCordinate);
            contentStream.lineTo(endX, yCordinate);
            contentStream.stroke();
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
    public void addHeader(PDDocument document, PDPage page, String myText, float x, float y) throws IOException {
        PDFont pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Regular.ttf"));
        float fontSize = 11;
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.beginText();
            if (myText.equals("Arkadia PC")) {
                pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Bold.TTF"));
            }
            contentStream.setFont(pdfFont, fontSize);
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
    public void addParagraph(PDDocument document, PDPage page, String myText, float x, float y) throws IOException {
        PDFont pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Regular.ttf"));
        float fontSize = 12;
        try {
            if (Objects.equals(myText, "* Pour les personnes utilisant le Chèque Emploi Service Universel, seul le montant financé personnellement est déductible. " +
                    "Une attestation est délivrée par les établissements préfinançant le CESU.")) {
                fontSize = 10;
                pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Italic.ttf"));
            }
            float leading = 1.5f * fontSize;
            PDRectangle mediabox = page.getMediaBox();
            float margin = MARGIN / 2;
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
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            PDImageXObject pdImage = PDImageXObject.createFromFile(imageName, document);
            if (imageName.equals("src/media/images/logoArkadiaPc-transformed.jpeg")) {
                contentStream.drawImage(pdImage, x + 30, y - 90, 110, 80);
            } else {
                contentStream.drawImage(pdImage, x, y - 100, 200, 70);
            }
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Définition des meta propriétés
     */
    public void setDocumentProperties() {
        // TODO par défaut créateur, auteur, titre, sujet
        PDDocumentInformation docInformation = this.document.getDocumentInformation();
        docInformation.setAuthor("Araujo Adelino");
        docInformation.setTitle("Certificate destinée au Centre des Impôts");
        docInformation.setCreator("ArkadiaPC");
        docInformation.setSubject("Certificate fiscale");
        docInformation.setCreationDate(Calendar.getInstance(Locale.FRANCE));
    }

    public boolean savePdf(CreateCertificate createCertificate) throws IOException {
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        FileOutputStream outputStream;
        fileChooser.setDialogTitle("Enregistrer sous");
        fileChooser.setSelectedFile(new File("Certificate-Fiscale-" + createCertificate.getFiscalYearTxt() + "-" + createCertificate.getCustomerNameTxt() + "-" + createCertificate.getCustomerFirstnameTxt() + ".pdf"));
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                if (fileToSave.createNewFile()) {
                    outputStream = new FileOutputStream(fileToSave.getAbsolutePath());
                    document.save(fileToSave);
                    outputStream.close();
                    document.close();
                    return true;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                document.close();
            }
        }
        document.close();
        return false;
    }
}