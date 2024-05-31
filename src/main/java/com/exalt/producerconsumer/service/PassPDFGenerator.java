package com.exalt.producerconsumer.service;

import com.exalt.producerconsumer.model.Pass;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PassPDFGenerator implements Serializable {

    @Autowired
    private final SortVIPService sortVIPService;

    @Autowired
    private BatchPassGenerator batchPassGenerator;

    public PassPDFGenerator(SortVIPService sortVIPService) {
        this.sortVIPService = sortVIPService;
    }

    public byte[] generatePassesPdf(int numberOfPasses) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        List<Pass> passes = batchPassGenerator.generatePassBatch(numberOfPasses);

        int i = 1;
        for (Pass pass : passes) {
            document.add(new Paragraph("Nom: " + pass.getFirstName()));
            document.add(new Paragraph("Prénom: " + pass.getLastName()));
            document.add(new Paragraph("Statut VIP: " + (pass.isStatusVIP() ? "Oui" : "Non")));
            document.add(new Paragraph("Date de naissance: " + pass.getDateOfBirth()));
            document.add(new Paragraph("Date/Heure du demande: " + pass.getDateTimeOfRequest()));
            document.add(new Paragraph("Date/Heure du génération: " + pass.getDateTimeOfGeneration()));
            document.add(new Paragraph("\n"));

            if(i%4==0){
                document.add(new Paragraph("\n"));
            } else{
                i++;
            }
        }

        document.close();
        return byteArrayOutputStream.toByteArray();
    }

    // List<Pass> passListToBeGenerated = sortVIPService.sortVIPPassList(passes);


/*        public byte[] generatePassesPdf(List<Pass> passes) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            PageSize pagesize = new PageSize(360, 300);
            Document document = new Document(pdfDoc, pagesize);


            for (Pass pass : passes) {
                    document.add(new Paragraph("Pass EXALT"));
                    document.add(new Paragraph("Nom: " + pass.getLastName()));
                    document.add(new Paragraph("Prénom: " + pass.getFirstName()));
                    document.add(new Paragraph("Statut VIP: " + (pass.isStatusVIP() ? "Oui" : "Non")));
                    document.add(new Paragraph("Date de naissance: " + pass.getDateOfBirth()));
                    document.add(new Paragraph("Date/Heure du demande: " + pass.getDateTimeOfRequest()));
                    document.add(new Paragraph("Date/Heure du génération: " + pass.getDateTimeOfGeneration()));
                    document.add(new Paragraph("\n"));
            }

            document.close();

            return byteArrayOutputStream.toByteArray();
        }*/

    public List<byte[]> generatePassesPdf(List<Pass> passes) {
        List<byte[]> pdfByteArrayList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        for (Pass pass : passes) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(byteArrayOutputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            PageSize pageSize = new PageSize(360, 300);
            Document document = new Document(pdfDoc, pageSize);

            document.add(new Paragraph("Passe EXALT"));
            document.add(new Paragraph("Nom: " + pass.getLastName()));
            document.add(new Paragraph("Prénom: " + pass.getFirstName()));
            document.add(new Paragraph("Statut VIP: " + (pass.isStatusVIP() ? "Oui" : "Non")));
            document.add(new Paragraph("Date de naissance: " + pass.getDateOfBirth()));
            document.add(new Paragraph("Date/Heure du demande: " + pass.getDateTimeOfRequest()));
            document.add(new Paragraph("Date/Heure du génération: " + dateFormat.format(new Date())));
            document.add(new Paragraph("\n"));

            document.close();

            pdfByteArrayList.add(byteArrayOutputStream.toByteArray());
        }

        return pdfByteArrayList;
    }

}
