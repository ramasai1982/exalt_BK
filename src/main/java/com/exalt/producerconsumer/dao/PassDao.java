package com.exalt.producerconsumer.dao;

import com.exalt.producerconsumer.exception.UserNotFoundException;
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

    public Pass save(Pass pass){
        passRepo.save(pass);
        return pass;
    }

    public Pass findById(Integer id){
        return passRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User id " + id + " was not found"));

    }

    public void deleteById(Integer id){
            passRepo.delete(findById(id));
    }

    public List<Pass> retrieveAllPasses(){
        List<Pass> passes = passRepo.findAll();
        System.out.println(passes);
        return passes;
    }
}