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

import static java.lang.System.out;

public class Certificate {

    static float MARGIN = 50;
    private final PDDocument document = new PDDocument();
    private final String p1;
    private final String pFiscYear;
    private final String pAmount;
    private final String p9;
    private final String p2;

    public Certificate(CreateCertificate createCertificate, EditCompany editCompany) throws IOException {

        // Création de la page unique taille A4
        PDPage page = new PDPage(PDRectangle.LETTER);
        document.addPage(page);

        /*
          Header gauche
          futur logo client et infos entreprise
         */
        // TODO prévoir insert logo
        String h1 = editCompany.getCompanyNameTxt();
        addText(document, page, h1, MARGIN, 750);
        addText(document, page, editCompany.getCompanyAddressTxt(), MARGIN, 735);
        addText(document, page, editCompany.getCompanyZipTxt() + " " + editCompany.getCompanyCityTxt(), MARGIN, 720);
        addText(document, page, editCompany.getCompanyTelTxt(), MARGIN, 705);
        addText(document, page, editCompany.getCompanyMailTxt(), MARGIN, 690);
        addText(document, page, editCompany.getCompanyApprovalTxt(), MARGIN, 660);

        /*
          Header droit
          logo client et infos client
         */
        addImage(document, page, "src/media/images/logoArkadiaPc-transformed.jpeg", MARGIN * 7.6f, 780); // logo
        addText(document, page, createCertificate.getCustomerTitleAbbrevCmb() + " " + createCertificate.getCustomerNameTxt() + " " + createCertificate.getCustomerFirstnameTxt(), MARGIN * 8, 660);
        addText(document, page, createCertificate.getCustomerAddressTxt(), MARGIN * 8, 645);
        addText(document, page, createCertificate.getCustomerZipTxt() + " " + createCertificate.getCustomerCityTxt(), MARGIN * 8, 630);
        addText(document, page, "le " + createCertificate.getDateAttestationFormat(), MARGIN * 7.3f, 605);

        /*
         Titre
         */
        addTitle(document, page, "Attestation destinée au Centre des Impôts", MARGIN * 3, 550);

        /*
         Body
         */
        p1 = tab() + "Je soussigné " + editCompany.getHolderTitleCmb() + " " + editCompany.getHolderFirstnameTxt() + " " + editCompany.getHolderNameTxt() + ", " +
                "gérant de l'organisme agréé " + editCompany.getCompanyNameTxt() + ", certifie que " + createCertificate.getCustomerTitleCmb() + " "
                + createCertificate.getCustomerFirstnameTxt() + " " + createCertificate.getCustomerNameTxt() + " a bénéficié d'assistance informatique à domicile, service à la personne :";
        addParagraph(document, page, p1, MARGIN, 500);

        /*
         Déconstruction du paragraphe afin d'ajouter fiscal year et certificate amount en bold
         Insertion dans la ligne p2 des différents strings formatés en bold
        */
        p2 = tab() + tab() + "Montant total des factures pour l'année ";
        addParagraph(document, page, p2, MARGIN, scalingY());
        pFiscYear = createCertificate.getFiscalYearTxt();
        addParagraph(document, page, pFiscYear, MARGIN * 6.25f, scalingY());
        String pDDots = " : ";
        addParagraph(document, page, pDDots, MARGIN * 6.75f, scalingY());
        pAmount = createCertificate.getCertificateAmountTxt();
        addParagraph(document, page, pAmount, MARGIN * 6.95f, scalingY());
        String pMoney = " €";
        addParagraph(document, page, pMoney, scalingMoney(), scalingY());


        String p3 = tab() + tab() + "Montant total payé en CESU préfinancé* : 0 €";
        addParagraph(document, page, p3, MARGIN, scalingY() - 15);//415
        String p4 = "Intervenants : ";
        addParagraph(document, page, p4, MARGIN, scalingY() - 45);//385
        String p5 = tab() + tab() + editCompany.getHolderFirstnameTxt() + " " + editCompany.getHolderNameTxt();
        addParagraph(document, page, p5, MARGIN, scalingY() - 75);//355
        String p6 = "Prestations : ";
        addParagraph(document, page, p6, MARGIN, scalingY() - 105);//325
        String p7 = tab() + "Les sommes perçues pour financer les services à la personne sont à déduire de la valeur indiquée précédemment.";
        addParagraph(document, page, p7, MARGIN, scalingY() - 135);//295
        String p8 = tab() + "La déclaration engage la responsabilité du seul contribuable.";
        addParagraph(document, page, p8, MARGIN, scalingY() - 175);//255
        p9 = "* Pour les personnes utilisant le Chèque Emploi Service Universel, seul le montant financé personnellement est déductible. " +
                "Une attestation est délivrée par les établissements préfinançant le CESU.";
        addParagraph(document, page, p9, MARGIN, scalingY() - 210);//225
        String p10 = tab() + "Fait pour valoir ce que de droit,";
        addParagraph(document, page, p10, MARGIN, scalingY() - 275);//155
        String p11 = editCompany.getHolderFirstnameTxt() + " " + editCompany.getHolderNameTxt() + ", gérant.";
        addParagraph(document, page, p11, MARGIN, scalingY() - 335);//115
        addImage(document, page, "src/media/images/signature.jpg", MARGIN * 6, scalingY() - 315);

        /*
          Meta propriétés du pdf
         */
        setDocumentProperties();

        out.println("La taille totale de p1 : " + getP1Length());
        out.println("La taille totale de p2 : " + getP2Length());
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
    public void addText(PDDocument document, PDPage page, String myText, float x, float y) throws IOException {
        EditCompany editCompany = new EditCompany();
        PDFont pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Regular.ttf"));
        float fontSize = 11;
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.beginText();
            if (myText.equals(editCompany.getCompanyNameTxt()) || myText.equals(editCompany.getCompanyApprovalTxt())
                    || myText.equals(pFiscYear) || myText.equals(pAmount)) {
                pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Bold.TTF"));
            }
            contentStream.setFont(pdfFont, fontSize);
            contentStream.newLineAtOffset(x, y);
            contentStream.showText(myText.trim());
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
        EditCompany editCompany = new EditCompany();
        float fontSize = 12;
        try {
            if (Objects.equals(myText, p9)) {
                fontSize = 10;
                pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Italic.ttf"));
            }
            if (myText.equals(editCompany.getCompanyNameTxt()) || myText.equals(editCompany.getCompanyApprovalTxt())
                    || myText.equals(pFiscYear) || myText.equals(pAmount)) {
                pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Bold.TTF"));
            }

            float leading = 1.5f * fontSize;
            PDRectangle mediabox = page.getMediaBox();
            float margin = MARGIN / 2;
            float width = mediabox.getWidth() - margin*3;
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
        } catch (
                IOException e) {
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
        docInformation.setTitle("Attestation destinée au Centre des Impôts");
        docInformation.setCreator("ArkadiaPC");
        docInformation.setSubject("Attestation fiscale");
        docInformation.setCreationDate(Calendar.getInstance(Locale.FRANCE));
    }


    /**
     * Méthode de sauvegarde du pdf
     */
    public boolean savePdf(CreateCertificate createCertificate) throws IOException {
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        FileOutputStream outputStream;
        fileChooser.setDialogTitle("Enregistrer sous");
        String file = String.valueOf(new File("Attestation-Fiscale-" + createCertificate.getFiscalYearTxt()
                + "-" + createCertificate.getCustomerNameTxt() + "-" + createCertificate.getCustomerFirstnameTxt() + ".pdf"));
        String replaceFileName = file.replace(' ', '-');
        fileChooser.setSelectedFile(new File(replaceFileName));
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


    /**
     * Y en fonction de p1.length()
     */
    public float scalingY() {
        if (p1.length() < 206) {
            return 450;
        } else {
            return 435;
        }
    }

    /**
     * X en fonction de pAmount
     */
    public float scalingMoney() {
        return MARGIN * (7.05f + (pAmount.length() / 10f));
    }


    /**
     * Impossible d'utiliser \t, méthode de contournement
     */
    private String tab() {
        return "            ";
    }

    /**
     * Méthodes dev, comptage total p1 p2
     */
    public String getP1Length() {
        return String.valueOf(p1.length());
    }

    public String getP2Length() {
        String pDDots = " : ";
        String pMoney = " €";
        return String.valueOf(p2.length() + pDDots.length() + pAmount.length() + pMoney.length());
    }
}
