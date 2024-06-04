package com.exalt.producerconsumer.controller;

import com.exalt.producerconsumer.model.Pass;
import com.exalt.producerconsumer.service.PassPDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pdf/")
public class PassPDFController {

    @Autowired
    private PassPDFGenerator passPDFGenerator;

    @PostMapping("generate")
    public ResponseEntity<Map<String, String>> generatePDF(@RequestBody List<Pass> passesForPDFGeneration){
        byte[] pdfBytesList = passPDFGenerator.generatePassesPdf(passesForPDFGeneration);

        String base64EncodedPdf = Base64.getEncoder().encodeToString(pdfBytesList);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "passes.pdf");

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("filename", "pass.pdf");
        responseMap.put("fileContent", base64EncodedPdf);


        return new ResponseEntity<>(responseMap, HttpStatus.OK);
    }

/*    @GetMapping("batch/generate-PDF/{numberOfPasses}")
    public ResponseEntity<byte[]> generatePDF(@PathVariable("numberOfPasses") int numberOfPasses){
        byte[] pdfBytes = passPDFGenerator.generatePassesPdf(numberOfPasses);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "passes.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdfBytes);
    }*/
}
