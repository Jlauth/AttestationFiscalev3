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
    private final String tCustTitleNameFirstN, tCustAddress, tCustZipCity;
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
        String tCompName = editCompany.getCompanyNameTxt();
        addText(document, page, tCompName, MARGIN, 750);
        String tCompAddress = editCompany.getCompanyAddressTxt();
        addText(document, page, tCompAddress, MARGIN, 735);
        String tCompZipCity = editCompany.getCompanyZipTxt() + " " + editCompany.getCompanyCityTxt();
        addText(document, page, tCompZipCity, MARGIN, 720);
        String tCompTel = editCompany.getCompanyTelTxt();
        addText(document, page, tCompTel, MARGIN, 705);
        String tCompMail = editCompany.getCompanyMailTxt();
        addText(document, page, tCompMail, MARGIN, 690);
        String tCompApproval = editCompany.getCompanyApprovalTxt();
        addText(document, page, tCompApproval, MARGIN, 660);

        /*
          Header droit
          logo client et infos client
         */
        String logoPath = "src/media/images/logoArkadiaPc-transformed.jpeg";
        addImage(document, page, logoPath, MARGIN * 7.6f, 780);
        tCustTitleNameFirstN = createCertificate.getCustomerTitleCmbShorted() + " " + createCertificate.getCustomerNameTxt() + " " + createCertificate.getCustomerFirstnameTxt();
        tCustAddress = createCertificate.getCustomerAddressTxt();
        tCustZipCity = createCertificate.getCustomerZipTxt() + " " + createCertificate.getCustomerCityTxt();
        addText(document, page, tCustTitleNameFirstN, calcCustRight(), 660);
        addText(document, page, tCustAddress, calcCustRight(), 645);
        addText(document, page, tCustZipCity, calcCustRight(), 630);
        String tCertDate = "Le " + createCertificate.getDateAttestationFormat() + ",";
        out.println(tCertDate);
        addText(document, page, tCertDate, calcCustRight() - 105, 605);
        //custInfoLength();

        /*
        Titre
        */
        addTitle(document, page, "Attestation fiscale destinée au Centre des Impôts", MARGIN * 2, 550);

        /*
         Body
         */
        // paragraphe fixe afin de toujours obtenir la taille correspondante
        p1 = tab() + "Je soussigné " + editCompany.getHolderTitleCmb() + " " + editCompany.getHolderFirstnameTxt() + " " + editCompany.getHolderNameTxt() + ", " +
                "gérant de l'organisme agréé " + editCompany.getCompanyNameTxt() + ", certifie que " + createCertificate.getCustomerTitleCmb() + " "
                + createCertificate.getCustomerFirstnameTxt() + " " + createCertificate.getCustomerNameTxt() +
                " a bénéficié d'assistance informatique à domicile, service à la personne :";
        String p1Large = "personne : ";
        String p1XL = "service à la personne";
        if (calcBodySize() > 1005) {
            String p101 = tab() + "Je soussigné " + editCompany.getHolderTitleCmb() + " " + editCompany.getHolderFirstnameTxt() + " " + editCompany.getHolderNameTxt() + ", " +
                    "gérant de l'organisme agréé " + editCompany.getCompanyNameTxt() + ", certifie que " + createCertificate.getCustomerTitleCmb() + " "
                    + createCertificate.getCustomerFirstnameTxt() + " " + createCertificate.getCustomerNameTxt() +
                    " a bénéficié d'assistance informatique à domicile, service à la ";
            addParagraph(document, page, p101, MARGIN, 500);
            addParagraph(document, page, p1Large, MARGIN, 465);
        } else if (calcBodySize() > 1020) {
            String p102 = tab() + "Je soussigné " + editCompany.getHolderTitleCmb() + " " + editCompany.getHolderFirstnameTxt() + " " + editCompany.getHolderNameTxt() + ", " +
                    "gérant de l'organisme agréé " + editCompany.getCompanyNameTxt() + ", certifie que " + createCertificate.getCustomerTitleCmb() + " "
                    + createCertificate.getCustomerFirstnameTxt() + " " + createCertificate.getCustomerNameTxt() +
                    " a bénéficié d'assistance informatique à domicile,";
            addParagraph(document, page, p102, MARGIN, 500);
            addParagraph(document, page, p1XL, MARGIN, 465);
        } else {
            addParagraph(document, page, p1, MARGIN, 500);
        }

        // Déconstruction du paragraphe afin d'ajouter fiscal year et certificate amount en bold
        //Insertion dans la ligne p2 des différents strings formatés en bold
        p2 = "Montant total des factures pour l'année ";
        addParagraph(document, page, p2, MARGIN + 70, scalingY());
        // année fiscale
        pFiscYear = createCertificate.getFiscalYearTxt();
        addParagraph(document, page, pFiscYear, 317, scalingY());

        // insert double points
        String pDDots = " : ";
        addParagraph(document, page, pDDots, 341, scalingY());
        // montant attestation fiscale
        pAmount = createCertificate.getCertificateAmountTxt();
        addParagraph(document, page, pAmount, 349, scalingY());
        // signe monétaire
        String pMoney = " €";
        addParagraph(document, page, pMoney, 349 + scalingMoney(), scalingY());

        String p3 = "Montant total payé en CESU préfinancé* : 0 €";
        addParagraph(document, page, p3, MARGIN + 70, scalingY() - 15);//415

        String p4 = "Intervenants : ";
        addParagraph(document, page, p4, MARGIN, scalingY() - 45);//385
        // prénom et nom gérant
        String p5 = editCompany.getHolderFirstnameTxt() + " " + editCompany.getHolderNameTxt();
        addParagraph(document, page, p5, MARGIN + 70, scalingY() - 75);//355
        String p6 = "Prestations : ";
        addParagraph(document, page, p6, MARGIN, scalingY() - 105);//325
        String p7 = tab() + "Les sommes perçues pour financer les services à la personne sont à déduire de la valeur indiquée précédemment.";
        addParagraph(document, page, p7, MARGIN, scalingY() - 135);//295
        String p8 = tab() + "La déclaration engage la responsabilité du seul contribuable.";
        addParagraph(document, page, p8, MARGIN, scalingY() - 175);//255
        p9 = "* Pour les personnes utilisant le Chèque Emploi Service Universel, seul le montant financé personnellement est déductible. " +
                "Une attestation est délivrée par les établissements préfinançant le CESU.";
        addParagraph(document, page, p9, MARGIN, scalingY() - 210);//225
        String p10 = "Fait pour valoir ce que de droit,";
        addParagraph(document, page, p10, MARGIN + 70, scalingY() - 275);//155
        // prénom et nom gérant
        String p11 = editCompany.getHolderFirstnameTxt() + " " + editCompany.getHolderNameTxt() + ", gérant.";
        addParagraph(document, page, p11, MARGIN, scalingY() - 335);//115
        // signature gérant
        addImage(document, page, "src/media/images/signature.jpg", MARGIN * 6, scalingY() - 315);

        /*
        Meta propriétés du pdf
        */
        setDocumentProperties();

        //out.println("La taille totale de p1 : " + getP1Length());
        //out.println("La taille totale de p2 : " + getP2Length());
        //calcCustRight();
        //calcBodySize();
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
        float startX = page.getCropBox().getLowerLeftX() + 100;
        float endX = page.getCropBox().getUpperRightX() - 105;
        PDFont pdFont = PDType0Font.load(document, new File("src/media/font/Calibri Bold.TTF"));
        float fontSize = 20;
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.PREPEND, true);
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
            if (myText.equals(editCompany.getCompanyNameTxt()) || myText.equals(editCompany.getCompanyApprovalTxt())) {
                pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Bold.TTF"));
            }
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            contentStream.beginText();
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
        EditCompany editCompany = new EditCompany();
        PDFont pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Regular.ttf"));
        float fontSize = 12;
        try {
            if (Objects.equals(myText, p9)) {
                fontSize = 11;
                pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Italic.ttf"));
            }
            if (myText.equals(editCompany.getCompanyNameTxt()) || myText.equals(editCompany.getCompanyApprovalTxt())
                    || myText.equals(pFiscYear) || myText.equals(pAmount)) {
                pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Bold.TTF"));
            }
            float leading = 1.5f * fontSize;
            PDRectangle mediabox = page.getMediaBox();
            float width = mediabox.getWidth() - 50;
            float startX = mediabox.getLowerLeftX();
            float startY = mediabox.getUpperRightY() - 20;
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
                int spaceIndex = myText.indexOf(' ', lastSpace + 1); // calcul de l'emplacement de spaceIndex en fonction de l'espace présent dans le texte
                if (spaceIndex < 0)
                    spaceIndex = myText.length(); // en début de phrase, spaceIndex étant 0, on y ajoute la taille totale de myText
                String subString = myText.substring(0, spaceIndex); // ajout du substring
                float size = fontSize * pdfFont.getStringWidth(subString) / 1000; // calcul de la taille du substring
                if (size > width) { // si la taille de la ligne est supérieure à la taille du mediabox
                    if (lastSpace < 0) {
                        lastSpace = spaceIndex;
                    }
                    subString = myText.substring(0, lastSpace);
                    lines.add(subString); // ajout d'une ligne complète
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
    public void savePdf(CreateCertificate createCertificate) throws IOException {
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
                    return;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                document.close();
            }
        }
        document.close();
    }


    /**
     * Y en fonction de p1.length()
     */
    public float scalingY() throws IOException {
        if (calcBodySize() <= 1005) {
            return 450;
        } else {
            return 435;
        }
    }

    /**
     * X en fonction de pAmount
     */
    public float scalingMoney() throws IOException {
        PDFont pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Regular.ttf"));
        int fontSize = 12;
        return (int) (fontSize * pdfFont.getStringWidth(pAmount) / 1000);
    }

    /**
     * Impossible d'utiliser \t, méthode de contournement
     */
    private String tab() {
        return "               ";
    }

    /**
     * Calcul de la taille du header droit afin d'auto aligner à gauche
     */
    public float calcCustRight() throws IOException {
        int fontSize = 11;
        PDFont pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Regular.ttf"));
        int calcSize = Math.max(tCustZipCity.length(), (Math.max(tCustTitleNameFirstN.length(), tCustAddress.length())));
        String biggestOne;
        if (calcSize == tCustZipCity.length()) {
            biggestOne = tCustZipCity;
        } else if (calcSize == tCustTitleNameFirstN.length()) {
            biggestOne = tCustTitleNameFirstN;
        } else {
            biggestOne = tCustAddress;
        }
        int size = (int) (fontSize * pdfFont.getStringWidth(biggestOne) / 1000);
        return 542 - size;
    }

    public int calcBodySize() throws IOException {
        PDFont pdfFont = PDType0Font.load(document, new File("src/media/font/Calibri Regular.ttf"));
        int fontSize = 12;
        return (int) (fontSize * pdfFont.getStringWidth(p1) / 1000);
    }
}
