package model;

import connect.CompanyDB;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import view.CertificateFrame;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import static java.lang.System.out;

/**
 * Classe Certificate représentant le modèle pour générer une attestation fiscale.
 * Récupère les données de la vue via un objet de la classe CertificateFrame.
 * Utilise une base de données de la classe CompanyDB pour récupérer les informations de l'entreprise.
 */
public class Certificate {

    /** Constante représentant la marge de la page. */
    static float MARGIN = 50;

    /** Document PDF en cours de création. */
    private final PDDocument document = new PDDocument();

    /** Premier paragraphe dynamique. */
    private final String p1;

    /** Année fiscale. */
    private final String pFiscYear;

    /** Montant total de l'attestation. */
    private final String pAmount;

    /** Titre, nom et prénom du client. */
    private final String tCustTNFN;

    /** Adresse du client. */
    private final String tCustAddress;

    /** Code postal et ville du client. */
    private final String tCustZipCity;

    /** Nom de l'entreprise. */
    private final String tCompName;

    /** Numéro d'agrément de l'entreprise. */
    private final String tCompApproval;

    /** Paragraphe dynamique du CESU. */
    private final String p9;

    /**
     * Construit une nouvelle attestation PDF en récupérant les données saisies dans le formulaire
     * Les données sont insérées dans le document PDF créé à l'aide de la librairie Apache PDFBox.
     * @param certificateFrame Le formulaire CertificateFrame contenant les données à insérer dans l'attestation.
     * @param companyDB La base de données CompanyDB contenant les informations de l'entreprise.
     * @throws IOException en cas d'erreur lors de la création du document PDF ou de l'écriture des données dans le fichier PDF.
     */
    public Certificate(CertificateFrame certificateFrame, CompanyDB companyDB) throws IOException {
        // Récupération des informations de l'entreprise enregistrées dans la base de données
        companyDB.selectCompanyInfo();
        // Création d'une nouvelle page PDF de taille A4
        PDPage page = new PDPage(PDRectangle.LETTER);
        document.addPage(page);

        /*
        Récupération des informations de l'entreprise depuis la base de données
         */
        tCompName = companyDB.getCompanyName();
        addText(document, page, tCompName, MARGIN, 750);
        String tCompAddress = companyDB.getCompanyAddress();
        addText(document, page, tCompAddress, MARGIN, 735);
        String tCompZipCity = companyDB.getCompanyZip() + " " + companyDB.getCompanyCity();
        addText(document, page, tCompZipCity, MARGIN, 720);
        String tCompTel = companyDB.getCompanyPhone();
        addText(document, page, tCompTel, MARGIN, 705);
        String tCompMail = companyDB.getCompanyMail();
        addText(document, page, tCompMail, MARGIN, 690);
        tCompApproval = companyDB.getCompanyApproval();
        addText(document, page, tCompApproval, MARGIN, 660);

       /*
        Ajout du logo client et des informations client dans le header droit
        */
        String logoPath = "C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\images\\logoArkadiaPc-transformed.jpeg";
        addImage(document, page, logoPath, 380, 780);
        tCustTNFN = certificateFrame.getCustomerTitleCmbShorted() + " " + certificateFrame.getCustomerNameTxt() + " " + certificateFrame.getCustomerFirstNTxt();
        tCustAddress = certificateFrame.getCustomerAddressTxt();
        tCustZipCity = certificateFrame.getCustomerZipTxt() + " " + certificateFrame.getCustomerCityTxt();
        addText(document, page, tCustTNFN, 541 - calcCustRight(), 660);
        addText(document, page, tCustAddress, 541 - calcCustRight(), 645);
        addText(document, page, tCustZipCity, 541 - calcCustRight(), 630);
        String tCertDate = "Le " + certificateFrame.getDateAttestationFormat() + ",";
        out.println(calcCustRight() + " right");
        if(calcCustRight()>95) {
            addText(document, page, tCertDate, 515 - calcCustRight(), 600);
        } else {
            addText(document, page, tCertDate, 414, 600);
        }

        // Ajout du titre "Attestation fiscale destinée au Centre des Impôts"
        addTitle(document, page, "Attestation fiscale destinée au Centre des Impôts", MARGIN * 2, 550);

        /*
         Construit le corps du certificat en fonction de la taille de la lettre et des informations de l'utilisateur.
         Génère un paragraphe fixe pour garantir la taille correspondante et ajoute un paragraphe dynamique
         en fonction de la taille de la lettre. La méthode calcule la taille du corps pour déterminer quel paragraphe dynamique
         doit être utilisé. Elle ajoute ensuite les paragraphes statiques et dynamiques au document PDF en utilisant la méthode addParagraph.
         */
        p1 = tab() + "Je soussigné " + companyDB.getHolderTitle() + " " + companyDB.getHolderFirstN() + " " + companyDB.getHolderName() + ", " +
                "gérant de l'organisme agréé " + companyDB.getCompanyName() + ", certifie que " + certificateFrame.getCustomerTitleCmb() + " "
                + certificateFrame.getCustomerFirstNTxt() + " " + certificateFrame.getCustomerNameTxt() +
                " a bénéficié d'assistance informatique à domicile, service à la personne :";
        String p1personne = "personne :";
        String p1laPers = "la personne : ";
        String p1personnel = "à la personne :";
        String p1ServAlaPers = "service à la personne :";
        String p1domicile = "domicile, service à la personne";
        if (calcBodySize() > 990 && calcBodySize() < 1020) {
            String p101 = tab() + "Je soussigné " + companyDB.getHolderTitle() + " " + companyDB.getHolderFirstN() + " " + companyDB.getHolderName() + ", " +
                    "gérant de l'organisme agréé " + companyDB.getCompanyName() + ", certifie que " + certificateFrame.getCustomerTitleCmb() + " "
                    + certificateFrame.getCustomerFirstNTxt() + " " + certificateFrame.getCustomerNameTxt() +
                    " a bénéficié d'assistance informatique à domicile, service à la ";
            addParagraph(document, page, p101, MARGIN, 500);
            addParagraph(document, page, p1personne, MARGIN, 465);
        } else if (calcBodySize() >= 1020 && calcBodySize() < 1050) {
            String p102 = tab() + "Je soussigné " + companyDB.getHolderTitle() + " " + companyDB.getHolderFirstN() + " " + companyDB.getHolderName() + ", " +
                    "gérant de l'organisme agréé " + companyDB.getCompanyName() + ", certifie que " + certificateFrame.getCustomerTitleCmb() + " "
                    + certificateFrame.getCustomerFirstNTxt() + " " + certificateFrame.getCustomerNameTxt() +
                    " a bénéficié d'assistance informatique à domicile, service à";
            addParagraph(document, page, p102, MARGIN, 500);
            addParagraph(document, page, p1laPers, MARGIN, 465);
        }
        else if (calcBodySize() >= 1050 && calcBodySize() < 1080){
            String p103 = tab() + "Je soussigné " + companyDB.getHolderTitle() + " " + companyDB.getHolderFirstN() + " " + companyDB.getHolderName() + ", " +
                    "gérant de l'organisme agréé " + companyDB.getCompanyName() + ", certifie que " + certificateFrame.getCustomerTitleCmb() + " "
                    + certificateFrame.getCustomerFirstNTxt() + " " + certificateFrame.getCustomerNameTxt() +
                    " a bénéficié d'assistance informatique à domicile, service ";
            addParagraph(document, page, p103, MARGIN, 500);
            addParagraph(document, page, p1personnel, MARGIN, 465);
        } else if (calcBodySize() >= 1080 && calcBodySize() < 1095)  {
            String p104 = tab() + "Je soussigné " + companyDB.getHolderTitle() + " " + companyDB.getHolderFirstN() + " " + companyDB.getHolderName() + ", " +
                    "gérant de l'organisme agréé " + companyDB.getCompanyName() + ", certifie que " + certificateFrame.getCustomerTitleCmb() + " "
                    + certificateFrame.getCustomerFirstNTxt() + " " + certificateFrame.getCustomerNameTxt() +
                    " a bénéficié d'assistance informatique à domicile, ";
            addParagraph(document, page, p104, MARGIN, 500);
            addParagraph(document, page, p1ServAlaPers, MARGIN, 465);
        } else if(calcBodySize() >= 1095 && calcBodySize() < 1125){
            String p105 = tab() + "Je soussigné " + companyDB.getHolderTitle() + " " + companyDB.getHolderFirstN() + " " + companyDB.getHolderName() + ", " +
                    "gérant de l'organisme agréé " + companyDB.getCompanyName() + ", certifie que " + certificateFrame.getCustomerTitleCmb() + " "
                    + certificateFrame.getCustomerFirstNTxt() + " " + certificateFrame.getCustomerNameTxt() +
                    " a bénéficié d'assistance informatique à ";
            addParagraph(document, page, p105, MARGIN, 500);
            addParagraph(document, page,p1domicile, MARGIN, 465 );
        } else {
            addParagraph(document, page, p1, MARGIN, 500);
        }

        // Décompose le paragraphe afin d'ajouter "année fiscale" et "montant d'attestation" en gras.
        // Insère les différentes chaînes de caractères formatées en gras dans la ligne p2.
        String p2 = "Montant total des factures pour l'année ";
        addParagraph(document, page, p2, MARGIN + 70, scalingY());

        // Année fiscale
        pFiscYear = certificateFrame.getFiscalYearTxt();
        addParagraph(document, page, pFiscYear, 317, scalingY());

        // Insertion de doubles points
        String pDDots = " : ";
        addParagraph(document, page, pDDots, 341, scalingY());

        // Montant d'attestation fiscale
        pAmount = certificateFrame.getCertificateAmountTxt();
        addParagraph(document, page, pAmount, 349, scalingY());

        // Symbole monétaire
        String pMoney = " €";
        addParagraph(document, page, pMoney, 349 + scalingMoney(), scalingY());

        // Ajoute le paragraphe fixe "Montant total payé en CESU préfinancé : 0 €"
        String p3 = "Montant total payé en CESU préfinancé* : 0 €";
        addParagraph(document, page, p3, MARGIN + 70, scalingY() - 15);//415

        // Ajoute le paragraphe fixe "Intervenants : "
        String p4 = "Intervenants : ";
        addParagraph(document, page, p4, MARGIN, scalingY() - 45);//385

        // Ajoute le prénom et le nom du gérant
        String p5 = companyDB.getHolderFirstN() + " " + companyDB.getHolderName();
        addParagraph(document, page, p5, MARGIN + 70, scalingY() - 75);//355

        // Ajoute le paragraphe fixe "Prestations : "
        String p6 = "Prestations : ";
        addParagraph(document, page, p6, MARGIN, scalingY() - 105);//325

        // Ajoute le paragraphe dynamique contenant les informations sur les sommes perçues pour financer les services à la personne
        String p7 = tab() + "Les sommes perçues pour financer les services à la personne sont à déduire de la valeur indiquée précédemment.";
        addParagraph(document, page, p7, MARGIN, scalingY() - 135);//295

        // Ajoute le paragraphe dynamique contenant les informations sur la responsabilité du contribuable
        String p8 = tab() + "La déclaration engage la responsabilité du seul contribuable.";
        addParagraph(document, page, p8, MARGIN, scalingY() - 175);//255

        // Ajoute le paragraphe dynamique contenant les informations sur le CESU
        p9 = "* Pour les personnes utilisant le Chèque Emploi Service Universel, seul le montant financé personnellement est déductible. " +
                "Une attestation est délivrée par les établissements préfinançant le CESU.";
        addParagraph(document, page, p9, MARGIN, scalingY() - 210);//225

        // Ajoute le paragraphe fixe "Fait pour valoir ce que de droit"
        String p10 = tab() + "Fait pour valoir ce que de droit,";
        addParagraph(document, page, p10, MARGIN, scalingY() - 275);//155

        // Ajoute le prénom et le nom du gérant
        String p11 = companyDB.getHolderFirstN() + " " + companyDB.getHolderName() + ", gérant.";
        addParagraph(document, page, p11, MARGIN, scalingY() - 335);//115

        // Signature gérant
        addImage(document, page, "C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\images\\signature.jpg", MARGIN * 6, scalingY() - 315);

        // Définition des propriétés du document PDF généré
        setDocumentProperties();
    }

    /**
     * Permet d'ajouter un titre à une page PDF avec une ligne de séparation en dessous.
     * @param document le document PDF concerné.
     * @param page la page PDF concernée.
     * @param myTitle le titre à afficher en tant que chaîne de caractères.
     * @param x la position horizontale du titre.
     * @param y la position verticale du titre.
     * @throws IOException si une erreur survient lors de l'écriture sur la page PDF.
     */
    public void addTitle(PDDocument document, PDPage page, String myTitle, float x, float y) throws IOException {
        float yCordinate = page.getCropBox().getUpperRightY() - 245;
        float startX = page.getCropBox().getLowerLeftX() + 100;
        float endX = page.getCropBox().getUpperRightX() - 105;
        PDFont pdFont = PDType0Font.load(document, new File("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\font\\Calibri Bold.TTF"));
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
     * Méthode d'ajout du header.
     * @param document le document PDF visé.
     * @param page la page PDF visée.
     * @param myText le texte à ajouter en tant que chaîne de caractères.
     * @param x la position horizontale en points.
     * @param y la position verticale en points.
     * @throws IOException si une erreur survient lors de l'écriture dans le contenu de la page PDF.
     */
    public void addText(PDDocument document, PDPage page, String myText, float x, float y) throws IOException {
        // Chargement de la police de caractères et déclaration de la taille de police
        PDFont pdfFont = PDType0Font.load(document, new File("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\font\\Calibri Regular.ttf"));
        float fontSize = 12;
        try {
            if (myText.equals(tCompName) || myText.equals(tCompApproval)) {
                pdfFont = PDType0Font.load(document, new File("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\font\\Calibri Bold.TTF"));
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
     * Méthode d'ajout d'un paragraphe.
     * @param document le document visé.
     * @param page     la page visée.
     * @param myText   le texte en String.
     * @param x        position x largeur.
     * @param y        position y hauteur.
     * @throws IOException si une erreur se produit lors de l'écriture du contenu sur la page.
     */
    public void addParagraph(PDDocument document, PDPage page, String myText, float x, float y) throws IOException {
        // Chargement de la police de caractères et déclaration de la taille de police
        PDFont pdfFont = PDType0Font.load(document, new File("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\font\\Calibri Regular.ttf"));
        float fontSize = 12;
        try {
            // Vérification du texte à afficher pour définir la police et la taille appropriées
            if (Objects.equals(myText, p9)) {
                pdfFont = PDType0Font.load(document, new File("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\font\\Calibri Light Italic.ttf"));
                fontSize = 11;
            }
            if (myText.equals(pFiscYear) || myText.equals(pAmount)) {
                pdfFont = PDType0Font.load(document, new File("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\font\\Calibri Bold.TTF"));
            }
            // Déclaration des variables pour la mise en forme du paragraphe
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
     * Méthode ajout d'une image.
     * @param document  le document visé.
     * @param page      la page visée.
     * @param imageName le nom de l'image en String.
     * @param x         position x largeur.
     * @param y         position y hauteur.
     */
    public void addImage(PDDocument document, PDPage page, String imageName, float x, float y) {
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            PDImageXObject pdImage = PDImageXObject.createFromFile(imageName, document);
            if (imageName.equals("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\images\\logoArkadiaPc-transformed.jpeg")) {
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
     * Définit les propriétés du document PDF.
     */
    public void setDocumentProperties() {
        PDDocumentInformation docInformation = this.document.getDocumentInformation();
        docInformation.setAuthor("Araujo Adelino");
        docInformation.setTitle("Attestation destinée au Centre des Impôts");
        docInformation.setCreator("ArkadiaPC");
        docInformation.setSubject("Attestation fiscale");
        docInformation.setCreationDate(Calendar.getInstance(Locale.FRANCE));
    }

    /**
     * Méthode permettant de sauvegarder le PDF généré.
     * @param certificateFrame le certificat à sauvegarder.
     * @throws IOException si une erreur d'entrée/sortie se produit.
     * @return true si la sauvegarde a réussi, false sinon.
     */
    public boolean savePdf(CertificateFrame certificateFrame) throws IOException {
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        FileOutputStream outputStream;
        fileChooser.setDialogTitle("Enregistrer sous");
        String file = String.valueOf(new File("Attestation-Fiscale-" + certificateFrame.getFiscalYearTxt()
                + "-" + certificateFrame.getCustomerNameTxt() + "-" + certificateFrame.getCustomerFirstNTxt() + ".pdf"));
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
                    return true; // renvoie true si la sauvegarde a réussi
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                document.close();
            }
        }
        document.close();
        return false; // renvoie false si la sauvegarde a échoué
    }


    /**
     * Calcule la valeur de l'axe Y à utiliser pour adapter la taille du corps du document en fonction de la longueur de p1.
     * Si la longueur de p1 est inférieure à 990, retourne 450, sinon retourne 435.
     * @return La valeur de l'axe Y pour adapter la taille du corps du document.
     * @throws IOException Si une erreur survient lors de la lecture ou de l'écriture du fichier PDF.
     */
    public float scalingY() throws IOException {
        if (calcBodySize() < 990) {
            return 450;
        } else {
            return 435;
        }
    }

    /**
     * Détermine la valeur de l'axe X en fonction du montant présenté.
     * @return la valeur de l'axe X.
     * @throws IOException si une erreur d'entrée/sortie se produit.
     */
    public float scalingMoney() throws IOException {
        PDFont pdfFont = PDType0Font.load(document, new File("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\font\\Calibri Regular.ttf"));
        int fontSize = 12;
        return (int)(fontSize * pdfFont.getStringWidth(pAmount) / 1000);
    }

    /**
     * Renvoie une chaîne de caractères correspondant à une indentation de 15 espaces.
     * Méthode créée, car il est impossible d'utiliser la tabulation (\t) avec PDFBox.
     * @return une chaîne de caractères avec 15 espaces.
     */
    private String tab() {
        return "               ";
    }

    /**
     * Calcule la taille nécessaire pour le header droit afin de l'aligner automatiquement à gauche.
     * @return La taille maximale nécessaire pour afficher les informations du client (code postal et ville, nom et prénom, adresse).
     * @throws IOException Si une erreur survient lors du chargement de la police PDF.
     */
    public float calcCustRight() throws IOException {
        PDFont pdfFont = PDType0Font.load(document, new File("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\font\\Calibri Regular.ttf"));
        int fontSize = 11;
        // calcul de la taille de chaque variable en fonction du font
        int sizeZC = (int) (fontSize * pdfFont.getStringWidth(tCustZipCity) / 1000);
        int sizeTNFN = (int) (fontSize * pdfFont.getStringWidth(tCustTNFN) / 1000);
        int sizeAddress = (int) (fontSize * pdfFont.getStringWidth(tCustAddress) / 1000);
        // comparaison et enregistrement taille du plus grand des trois
        return Math.max(sizeZC, (Math.max(sizeTNFN, sizeAddress)));
    }

    /**
     * Calcule la taille du corps du texte de l'attestation en fonction de la police et de la taille de police utilisées.
     * @return La taille en pixels de la zone de texte pour le corps du texte.
     * @throws IOException si une erreur se produit lors du chargement de la police.
     */
    public int calcBodySize() throws IOException {
        PDFont pdfFont = PDType0Font.load(document, new File("C:\\Users\\Jean\\IdeaProjects\\AttestationFiscalev3\\src\\media\\font\\Calibri Regular.ttf"));
        int fontSize = 12;
        return (int) (fontSize * pdfFont.getStringWidth(p1) / 1000);
    }
}
