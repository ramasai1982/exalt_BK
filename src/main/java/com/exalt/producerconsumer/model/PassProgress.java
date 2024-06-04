package com.exalt.producerconsumer.model;


import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PassProgress {
    private String passProgressCode;
    private int passTotalRequest;
    private int passTotalComplete;
}
