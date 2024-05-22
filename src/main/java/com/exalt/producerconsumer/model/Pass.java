package com.exalt.producerconsumer.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Component
@Entity
public class Pass{

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPass;
    private String firstName;
    private String lastName;
    private boolean statusVIP;
    private Date dateOfBirth;
    private String dateTimeOfRequest;
    private String dateTimeOfGeneration;

    public Pass(String firstName, String lastName, boolean statusVIP, Date dateOfBirth, String dateTimeOfRequest, String dateTimeOfGeneration) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.statusVIP = statusVIP;
        this.dateOfBirth = dateOfBirth;
        this.dateTimeOfRequest = dateTimeOfRequest;
        this.dateTimeOfGeneration = dateTimeOfGeneration;
    }
}
