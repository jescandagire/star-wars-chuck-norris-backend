package com.zatecexercise.zatectechnical.controller;

import com.zatecexercise.zatectechnical.config.OperationResult;
import com.zatecexercise.zatectechnical.exception.SearchKeyLengthException;
import com.zatecexercise.zatectechnical.service.JokesAndPeopleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@Slf4j
@Validated
public class JokesAndPeopleController {
    @Autowired
    JokesAndPeopleService jokesAndPeopleService;

    @GetMapping("/chuck/categories")
    public ResponseEntity<OperationResult> getALLJokesCategories(){
        log.info("Inside getALLJokesCategories method of JokesAndPeopleController");
        return ResponseEntity.status(HttpStatus.OK).body(jokesAndPeopleService.findAllJokesCategories());
    }

    @GetMapping("/chuck/categories/details")
    public ResponseEntity<OperationResult> getJokeCategoryDetails(@RequestParam("jokeName") String jokeName){
        log.info("Inside getJokeCategoryDetails method of JokesAndPeopleController");

        return ResponseEntity.status(HttpStatus.OK).body(jokesAndPeopleService.getJokeCategoryDetails(jokeName));
    }

    @GetMapping("/swapi/people")
    public ResponseEntity<OperationResult> getAllPeople(){
        log.info("Inside getAllPeople method of JokesAndPeopleController");
        return ResponseEntity.status(HttpStatus.OK).body(jokesAndPeopleService.findAllPeople());
    }

    @GetMapping("/search")
    public ResponseEntity<OperationResult> searchJokeOrPerson(@RequestParam("searchKey") String searchKey) {
        log.info("Inside searchJokeOrPerson method of JokesAndPeopleController");

        return ResponseEntity.status(HttpStatus.OK).body(jokesAndPeopleService.searchJokeOrPerson(searchKey));
    }
}
