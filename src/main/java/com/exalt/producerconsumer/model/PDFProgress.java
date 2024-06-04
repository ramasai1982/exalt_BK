package com.exalt.producerconsumer.model;


import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PDFProgress {

    private String pdfProgressCode;
    private int pdfVIPTotalRequest;
    private int pdfVIPTotalComplete;
    private int pdfNoVIPTotalRequest;
    private int pdfNoVIPTotalComplete;

}
