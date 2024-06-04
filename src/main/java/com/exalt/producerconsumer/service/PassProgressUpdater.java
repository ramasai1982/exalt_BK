package com.exalt.producerconsumer.service;

import com.exalt.producerconsumer.model.PassProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PassProgressUpdater {

    @Autowired
    private final PassProgress passProgress;

    private HashMap<String, PassProgress> mapProgress = new HashMap<>();

    public PassProgressUpdater(PassProgress passProgress) {
        this.passProgress = passProgress;
    }

    public void setPassProgress(PassProgress inPassProgress, String batchCode){
        if(mapProgress.containsKey(batchCode)){
            mapProgress.replace(batchCode, inPassProgress);
            System.out.println(inPassProgress);
        }else {
            mapProgress.put(batchCode, inPassProgress);
            System.out.println(inPassProgress);
        }
    }

    public PassProgress getPassProgress(String batchCode){
            return mapProgress.get(batchCode);
    }

}
