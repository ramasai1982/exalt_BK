package com.exalt.producerconsumer.service;


import com.exalt.producerconsumer.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PersonGenerator {

    @Autowired
    private final DateOfBirthGenerator dateOfBirth;
    @Autowired
    private final NameGenerator nameGenerator;

    public PersonGenerator(DateOfBirthGenerator dateOfBirth, NameGenerator nameGenerator) {
        this.dateOfBirth = dateOfBirth;
        this.nameGenerator = nameGenerator;
    }

    // Generates pass in batch with the required inPut of number of passes
    // date of birth, date & time of generation, date & time of request are taken as current date and time
    public List<Person> generatePersonForPass(int numberOfPasses) {

        List<Person> personList = new ArrayList<>();

        for(int i = 1; i<=numberOfPasses; i++){
            Person person = new Person(nameGenerator.nameGenerator(), nameGenerator.nameGenerator(), new Random().nextBoolean(), dateOfBirth.dateOfBirth());
            personList.add(person);
        }
        return personList;
    }
}
