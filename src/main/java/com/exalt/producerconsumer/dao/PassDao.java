package com.exalt.producerconsumer.dao;

import com.exalt.producerconsumer.model.Pass;
import com.exalt.producerconsumer.repo.PassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PassDao {

    @Autowired
    private final PassRepo passRepo;

    public PassDao(PassRepo passRepo) {
        this.passRepo = passRepo;
    }

    public void saveToDatabase(List<Pass> passes){
        passRepo.saveAll(passes);
    }

    public List<Pass> retrieveAllPasses(){
        return passRepo.findAll();
    }
}