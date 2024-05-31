package com.exalt.producerconsumer.controller;

import com.exalt.producerconsumer.dao.PassDao;
import com.exalt.producerconsumer.model.Pass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/db/")
public class DbPassController {
    private final PassDao passDao;

    @Autowired
    public DbPassController(PassDao passDao) {
        this.passDao = passDao;
    }

    @GetMapping("list-all")
    public ResponseEntity<List<Pass>> getAllPasses()  {
        List<Pass> passes = passDao.retrieveAllPasses();
        return new ResponseEntity<>(passes, HttpStatus.OK);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Pass> findById(@PathVariable("id") Integer id)  {
        Pass pass = passDao.findById(id);
        return new ResponseEntity<>(pass, HttpStatus.OK);
    }

    @PutMapping("edit")
    public ResponseEntity<Pass> editById(@RequestBody Pass pass)  {
        Pass passReturn = passDao.save(pass);
        return new ResponseEntity<>(passReturn, HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Pass> addPass(@RequestBody Pass pass)  {
        System.out.println(pass + " " + "here");
/*        Pass passAdd = new Pass(pass.getFirstName(),
                                pass.getLastName(),
                                pass.isStatusVIP(),
                                pass.getDateOfBirth(),
                                pass.getDateTimeOfRequest(),
                                pass.getDateTimeOfGeneration());
        System.out.println(passAdd.getLastName());
        System.out.println(passAdd.getId_pass());*/
                        passDao.save(pass);
        return new ResponseEntity<>(pass, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<List<Pass>> deleteById(@PathVariable("id") Integer id)  {
        System.out.println(id);
        passDao.deleteById(id);
        List<Pass> passes = passDao.retrieveAllPasses();
        return new ResponseEntity<>(passes,HttpStatus.OK);
    }

}
