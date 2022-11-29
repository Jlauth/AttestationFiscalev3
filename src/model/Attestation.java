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
    PDDocument document = new PDDocument();

    public Attestation(Creer creer, EditerEntreprise editerEntreprise) {
        // Set propriétés du doc
        setDocumentProperties(document);
        // Ajout d'une page
        PDPage page = new PDPage(PDRectangle.LETTER);
        document.addPage(page);

        // Header droit
        // Import logo
        insertImage(document, page, "src/media/logoArkadiaPc-transformed.jpeg", 420, 780);
        // Infos client
        addText(document, page, "" + creer.getCmbTitre(), 420, 650);
        addText(document, page, "" + creer.getTxtNomClient() + " " + creer.getTxtPrenomClient(), 420, 635);
        addText(document, page, "" + creer.getTxtAdresseClient(), 420, 620);
        addText(document, page, "" + creer.getTxtCPClient() + " " + creer.getTxtVilleClient(), 420, 605);
        // Date émission attestation
        addText(document, page, "le " + creer.getDateAttestation() + ",", 380, 570);

        // Titre
        addTitle(document, page, "Attestation destinée au Centre des Impôts", 150, 530);

        // Header gauche
        // Infos entreprise
        addText(document, page, "" + editerEntreprise.getTxtNomEntreprise(), MARGIN, 750);
        addText(document, page, "" + editerEntreprise.getTxtAdresseEntreprise(), MARGIN, 735);
        addText(document, page, "" + editerEntreprise.getTxtCPEntreprise() + " " + editerEntreprise.getTxtVilleEntreprise(), MARGIN, 720);
        addText(document, page, "" + editerEntreprise.getTxtTelEntreprise(), MARGIN, 705);
        addText(document, page, "" + editerEntreprise.getTxtMailEntreprise(), MARGIN, 690);
        addText(document, page, "" + editerEntreprise.getTxtAgrement(), MARGIN, 670);

        // Add paragraph
        String p1 = "Je soussigné " + editerEntreprise.getCmbTitreGerant() + " " + editerEntreprise.getTxtPrenomGerant() + " " + editerEntreprise.getTxtNomGerant() + ", " +
                "gérant de l'organisme agréé " + editerEntreprise.getTxtNomEntreprise() + ", certifie que " + creer.getCmbTitre() + " " + creer.getTxtPrenomClient() + " " +
                creer.getTxtNomClient() + " a bénéficié d'assistance informatique à domicile, service à la personne.";
        String p2 = "Montant total des factures pour l'année " + creer.getExerciceFiscal() + " : " + creer.getTxtMontantAttest() + "€";
        String p3 = "Montant total payé en CESU préfinancé : 0 €";
        String p4 = "Intervenants : ";
        String p5 = editerEntreprise.getTxtPrenomGerant() + " " + editerEntreprise.getTxtNomGerant();
        String p6 = "Prestations : ";
        String p7 = "Les sommes perçues pour financer les services à la personne sont à déduire de la valeur indiquée précédemment.";
        String p8 = "La déclaration engage la responsabilité du seul contribuable";
        String p9 = "* Pour les personnes utilisant le Chèque Emploi Service Universel, seul le montant financé personnellement est déductible. " +
                "Une attestation est délivrée par les établissements préfinançant le CESU.";
        String p10 = "Fait pour valoir ce que de droit,";
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
    }

    public void setDocumentProperties(PDDocument document) {
        // Creating the PDDocumentInformation object
        PDDocumentInformation docInformation = document.getDocumentInformation();
        // Setting the author of the document
        docInformation.setAuthor("Araujo Adelino");
        // Setting the title of the document
        docInformation.setTitle("Attestation destinée au Centre des Impôts");
        // Setting the creator of the document
        docInformation.setCreator("ArkadiaPC");
        // Setting the subject of the document
        docInformation.setSubject("Attestation fiscale");
        // Setting the created date of the document
        Calendar date = new GregorianCalendar();
        date.set(2022, 11, 28);
        docInformation.setCreationDate(date);
        docInformation.setModificationDate(date);
        // Setting keywords for the document
        docInformation.setKeywords("ArkadiaPC, attestation, fiscale");
    }

    public void insertImage(PDDocument document, PDPage page, String imageName, float x, float y) {
        try {
            // Get Content Stream for Writing Data
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            // Creating PDImageXObject object
            PDImageXObject pdImage = PDImageXObject.createFromFile(imageName, document);
            // Drawing the image in the PDF document
            contentStream.drawImage(pdImage, x, y - 100, 100, 100);
            // Closing the content stream
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addText(PDDocument document, PDPage page, String myText, float x, float y) {
        try {
            // Get Content Stream for Writing Data
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            // Begin the Content stream
            contentStream.beginText();
            // Setting the font to the Content stream
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            // Setting the position for the line
            contentStream.newLineAtOffset(x, y);
            // Adding text in the form of string
            contentStream.showText(myText);
            // Ending the content stream
            contentStream.endText();
            // Closing the content stream
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addTitle(PDDocument document, PDPage page, String myTitle, float x, float y) {
        try {
            // Get Content Stream for Writing Data
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
            // Begin the Content stream
            contentStream.beginText();
            // Setting the font to the Content stream
            contentStream.setFont(PDType1Font.HELVETICA, 16);
            // Setting the position for the line
            contentStream.newLineAtOffset(x, y);
            // Adding text in the form of string
            contentStream.showText(myTitle);
            // Ending the content stream
            contentStream.endText();
            // Closing the content stream
            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addParagraph(PDDocument document, PDPage page, String myText, float x, float y) {
        try {
            // Setting the font
            PDFont pdfFont = PDType1Font.HELVETICA;
            float fontSize = 11;
            float leading = 1.5f * fontSize;
            // Get the Width and X/Y coordinates
            PDRectangle mediabox = page.getMediaBox();
            float margin = MARGIN;
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
            // Split the paragraph based on width and font size into multiple lines
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
            // Get Content Stream for Writing Data
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

    public void save() throws IOException {
        Creer creer = new Creer();
        String filePath = "C:\\Users\\Jean\\Documents\\AttestationsFiscalesTest\\";
        String fileName = "AttestationFiscale-" +
                creer.getExerciceFiscal() + "-" + creer.getTxtNomClient() + "-" + creer.getTxtPrenomClient() + ".pdf";
        document.save(filePath+fileName);
        System.out.println("Sauvegardé, path : " + fileName );
        // Closing the document
        document.close();
    }
}