package com.exalt.producerconsumer.controller;


import com.exalt.producerconsumer.model.PDFProgress;
import com.exalt.producerconsumer.model.PassProgress;
import com.exalt.producerconsumer.model.PersonProgress;
import com.exalt.producerconsumer.service.PDFProgressUpdater;
import com.exalt.producerconsumer.service.PassProgressUpdater;
import com.exalt.producerconsumer.service.PersonProgressUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/progress/")
public class ProgressController {

    @Autowired
    private PassProgressUpdater passProgressUpdater;

    @Autowired
    private PersonProgressUpdater personProgressUpdater;

    @Autowired
    private PDFProgressUpdater pdfProgressUpdater;

    @GetMapping("pass/{batchCode}")
    public ResponseEntity<PassProgress> generatePassBatch(@PathVariable String batchCode) throws InterruptedException {
        PassProgress passProgress = passProgressUpdater.getPassProgress(batchCode);
        return new ResponseEntity<>(passProgress, HttpStatus.OK);
    }

    @GetMapping("person/{batchCode}")
    public ResponseEntity<PersonProgress> generatePersonBatch(@PathVariable String batchCode) throws InterruptedException {
        PersonProgress personProgress = personProgressUpdater.getPersonProgress(batchCode);
        return new ResponseEntity<>(personProgress, HttpStatus.OK);
    }

    @GetMapping("pdf/{batchCode}")
    public ResponseEntity<PDFProgress> generatePdf(@PathVariable String batchCode) throws InterruptedException {
        PDFProgress pdfProgress = pdfProgressUpdater.getPDFProgress(batchCode);
        return new ResponseEntity<>(pdfProgress, HttpStatus.OK);
    }
}
