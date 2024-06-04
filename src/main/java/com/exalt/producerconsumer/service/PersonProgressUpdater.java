package com.exalt.producerconsumer.service;

import com.exalt.producerconsumer.model.PersonProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PersonProgressUpdater {

    @Autowired
    private final PersonProgress personProgress;

    private final HashMap<String, PersonProgress> mapProgress = new HashMap<>();


    public PersonProgressUpdater(PersonProgress personProgress) {
        this.personProgress = personProgress;
    }

    public void setPersonProgress(PersonProgress inPersonProgress, String batchCode){
        if(mapProgress.containsKey(batchCode)){
            mapProgress.replace(batchCode, inPersonProgress);
            System.out.println(inPersonProgress);
        }else {
            mapProgress.put(batchCode, inPersonProgress);
            System.out.println(inPersonProgress);
        }
    }

    public PersonProgress getPersonProgress(String batchCode){
        return mapProgress.get(batchCode);
    }

}
