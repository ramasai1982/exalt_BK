package com.exalt.producerconsumer.service;


import com.exalt.producerconsumer.model.Person;
import com.exalt.producerconsumer.model.PersonProgress;
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
    @Autowired
    private final PersonProgressUpdater personProgressUpdater;
    @Autowired
    private PersonProgress personProgress;

    public PersonGenerator(DateOfBirthGenerator dateOfBirth, NameGenerator nameGenerator, PersonProgressUpdater personProgressUpdater, PersonProgress personProgress) {
        this.dateOfBirth = dateOfBirth;
        this.nameGenerator = nameGenerator;
        this.personProgressUpdater = personProgressUpdater;
        this.personProgress = personProgress;
    }

    // Generates pass in batch with the required inPut of number of passes
    // date of birth, date & time of generation, date & time of request are taken as current date and time
    public List<Person> generatePersonForPass(int numberOfPasses, String batchCode) throws InterruptedException {

        List<Person> personList = new ArrayList<>();
        personProgress = new PersonProgress(batchCode, numberOfPasses, 0);
        for(int i = 1; i<=numberOfPasses; i++){
            Person person = new Person(nameGenerator.nameGenerator(), nameGenerator.nameGenerator(), new Random().nextBoolean(), dateOfBirth.dateOfBirth());
            personList.add(person);
            personProgress.setPersonTotalComplete(personList.size());
            personProgressUpdater.setPersonProgress(personProgress, batchCode);
            Thread.sleep(1000);
        }
        return personList;
    }
}
