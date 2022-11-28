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

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Attestation {

    private Creer creer;
    static float MARGIN = 25;
    PDDocument document = new PDDocument();

    public Attestation(Creer creer) {
        this.creer = creer;
        // Set document properties
        setDocumentProperties(document);
        // Add a page to the document with proper size
        PDPage page = new PDPage(PDRectangle.LETTER);
        document.addPage(page);
        // Insert an Image
        insertImage(document, page, "src/media/logoArkadiaPc.jpg", 500, 780);
        // Add some Text
        addText(document, page, "My sample TEXT for PDF", MARGIN, 650);
        // Add paragraph
        String data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        addParagraph(document, page, data, 0, 625);
        // Draw Line
        drawLines(document, page, 25, 500);
        // Draw Rectangle
        drawRectangle(document, page, 25, 450);
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
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
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

    public void addParagraph(PDDocument document, PDPage page, String myText, float x, float y) {
        try {
            // Setting the font
            PDFont pdfFont = PDType1Font.TIMES_ROMAN;
            float fontSize = 12;
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
            contentStream.setNonStrokingColor(Color.RED);
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

    public void drawLines(PDDocument document, PDPage page, float x, float y) {
        try {
            PDRectangle mediabox = page.getMediaBox();
            float margin = MARGIN;
            float width = mediabox.getWidth() - (2 * margin);

            // Get Content Stream for Writing Data
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

            // Setting the non stroking color
            contentStream.setStrokingColor(Color.GREEN);

            // lets make some lines
            contentStream.moveTo(x, y);
            contentStream.lineTo(x + width, y);
            contentStream.lineTo(x + width, y + 25);
            contentStream.lineTo(x, y + 25);
            contentStream.stroke();

            contentStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawRectangle(PDDocument document, PDPage page, float x, float y) {
        try {
            PDRectangle mediabox = page.getMediaBox();
            float margin = MARGIN;
            float width = mediabox.getWidth() - (2 * margin);

            // Get Content Stream for Writing Data
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

            // Setting the non stroking color
            contentStream.setNonStrokingColor(Color.LIGHT_GRAY);

            // Drawing a rectangle
            contentStream.addRect(x, y, width, 25);

            // Drawing a rectangle
            // contentStream.fill();
            contentStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() throws IOException {
        document.save("Test.pdf");
        // Closing the document
        document.close();
    }
}