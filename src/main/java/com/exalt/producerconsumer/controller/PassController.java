package com.exalt.producerconsumer.controller;

import com.exalt.producerconsumer.dao.PassDao;
import com.exalt.producerconsumer.model.Pass;
import com.exalt.producerconsumer.service.BatchPassGenerator;
import com.exalt.producerconsumer.service.PassPDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class PassController {

    private final PassDao passDao;

    @Autowired
    private BatchPassGenerator batchPassGenerator;

    @Autowired
    private PassPDFGenerator passPDFGenerator;

    @Autowired
    public PassController(PassDao passDao) {
        this.passDao = passDao;
    }
    /*    private final SinglePassGenerator singlePassGenerator;*/

/*    public PassResource(SinglePassGenerator singlePassGenerator) {
        this.singlePassGenerator = singlePassGenerator;
    }*/

    @GetMapping("batch/{numberOfPasses}")
    public ResponseEntity<List<Pass>> generatePassBatch(@PathVariable("numberOfPasses") int numberOfPasses) {
        List<Pass> passes = batchPassGenerator.generatePassBatch(numberOfPasses);
        return new ResponseEntity<>(passes, HttpStatus.CREATED);
    }

    @GetMapping("batch-new/{numberOfPasses}")
    public ResponseEntity<List<Pass>> generatePassNewBatch(@PathVariable("numberOfPasses") int numberOfPasses)  {
        batchPassGenerator.deleteLocallyCreatedBatchPasses();
        List<Pass> passes = batchPassGenerator.generatePassBatch(numberOfPasses);
        return new ResponseEntity<>(passes, HttpStatus.CREATED);
    }

    @PostMapping("batch/store-to-db")
    public ResponseEntity<List<Pass>> saveToDatabase(@RequestBody List<Pass> passesTobeStoredInDatabase){
        System.out.println(passesTobeStoredInDatabase);
        passDao.saveToDatabase(passesTobeStoredInDatabase);
        return new ResponseEntity<>(passesTobeStoredInDatabase, HttpStatus.CREATED);
    }

    @GetMapping("get-local-list")
    public ResponseEntity<List<Pass>> getLocalList(){
        List<Pass> passListLocal= batchPassGenerator.getLocalPasses();
        return new ResponseEntity<>(passListLocal, HttpStatus.OK);
    }

    @DeleteMapping("batch/delete-local-list")
    public ResponseEntity<List<Pass>> deleteCreatedList(){
        List<Pass> passListCleared= batchPassGenerator.deleteLocallyCreatedBatchPasses();
        return new ResponseEntity<>(passListCleared, HttpStatus.OK);
    }

/*    @GetMapping("listAll")
    public ResponseEntity<List<Pass>> retrieveAllPasses(){
        List<Pass> allPassesFromDB = passDao.retrieveAllPasses();
        return new ResponseEntity<>(allPassesFromDB, HttpStatus.OK);
    }*/

    @PostMapping("batch/generate-PDF")
    public ResponseEntity<List<Map<String, String>>> generatePDF(@RequestBody List<Pass> passesForPDFGeneration){
        List<byte[]> pdfBytesList = passPDFGenerator.generatePassesPdf(passesForPDFGeneration);
        List<Map<String, String>> responseList = new ArrayList<>();

        int count = 1;
        for (byte[] pdfBytes : pdfBytesList) {
            String base64EncodedPdf = Base64.getEncoder().encodeToString(pdfBytes);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "passes_" + count + ".pdf");

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("filename", "pass_" + count + ".pdf");
            responseMap.put("fileContent", base64EncodedPdf);

            responseList.add(responseMap);
            count++;
        }

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("batch/generate-PDF/{numberOfPasses}")
    public ResponseEntity<byte[]> generatePDF(@PathVariable("numberOfPasses") int numberOfPasses){
        byte[] pdfBytes = passPDFGenerator.generatePassesPdf(numberOfPasses);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "passes.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdfBytes);
    }

}

