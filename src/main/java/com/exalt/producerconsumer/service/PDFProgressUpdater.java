package com.exalt.producerconsumer.service;

import com.exalt.producerconsumer.model.PDFProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PDFProgressUpdater {


    @Autowired
    private final PDFProgress pdfProgress;

    private HashMap<String, PDFProgress> mapProgress = new HashMap<>();

    public PDFProgressUpdater(PDFProgress pdfProgress) {
        this.pdfProgress = pdfProgress;
    }

    public void setPDFProgress(PDFProgress inPDFProgress, String batchCode){
        if(mapProgress.containsKey(batchCode)){
            mapProgress.replace(batchCode, inPDFProgress);
            System.out.println(inPDFProgress);
        }else {
            mapProgress.put(batchCode, inPDFProgress);
            System.out.println(inPDFProgress);
        }
    }

    public PDFProgress getPDFProgress(String batchCode){
        return mapProgress.get(batchCode);
    }

}
