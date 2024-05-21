package com.exalt.producerconsumer.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Person {
    private String firstName;
    private String lastName;
    private boolean statusVIP;
    private Date dateOfBirth;
}
