package com.exalt.producerconsumer.model;


import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PersonProgress {

    private String personCode;
    private int personTotalRequest;
    private int personTotalComplete;
}
