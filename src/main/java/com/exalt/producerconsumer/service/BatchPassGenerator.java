package com.exalt.producerconsumer.service;

import com.exalt.producerconsumer.model.Pass;
import com.exalt.producerconsumer.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BatchPassGenerator {
    private final List<Pass> allPasses = new ArrayList<>();

    @Autowired
    private final SortVIPService sortVIPService;
    @Autowired
    private final PersonGenerator personGenerator;

    public BatchPassGenerator(SortVIPService sortVIPService, PersonGenerator personGenerator) {
        this.sortVIPService = sortVIPService;
        this.personGenerator = personGenerator;
    }

    // Generates pass in batch with required inPut list of passes with minimum required fields
    // required -> first name, last name, vip status & date of birth
    public List<Pass> generatePassBatch(int numberOfPasses)  {

        List<Person> personList;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dateTimeOfRequest = new Date();

        //method called to generate list of persons
        personList = personGenerator.generatePersonForPass(numberOfPasses);

        //method called to sort the list to get VIPs on the top of the list
        List<Person> personListToBeGenerated = sortVIPService.sortVIPList(personList);

        //generate all passes
        for(Person personList2 : personListToBeGenerated){
            //Thread.sleep(1000);
            Pass pass = new Pass(personList2.getFirstName(),
                    personList2.getLastName(),
                    personList2.isStatusVIP(),
                    personList2.getDateOfBirth(),
                    dateFormat.format(dateTimeOfRequest),
                    dateFormat.format(new Date()));
            allPasses.add(pass);
        }

        return allPasses;
    }

    public List<Pass> deleteLocallyCreatedBatchPasses(){
        allPasses.clear();
        return allPasses;
    }

}
