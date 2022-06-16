package com.zatecexercise.zatectechnical.controller;

import com.zatecexercise.zatectechnical.config.OperationResult;
import com.zatecexercise.zatectechnical.service.JokesAndPeopleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class JokesAndPeopleController {
    @Autowired
    JokesAndPeopleService jokesAndPeopleService;

    @GetMapping("/chuck/categories")
    public ResponseEntity<OperationResult> getALLJokesCategories(){
        log.info("Inside getALLJokesCategories method of JokesAndPeopleController");
        return ResponseEntity.status(HttpStatus.OK).body(jokesAndPeopleService.findAllJokesCategories());
    }

    @GetMapping("/swapi/people")
    public ResponseEntity<OperationResult> getAllPeople(){
        log.info("Inside getAllPeople method of JokesAndPeopleController");
        return ResponseEntity.status(HttpStatus.OK).body(jokesAndPeopleService.findAllPeople());
    }

    @GetMapping("/search/joke")
    public ResponseEntity<OperationResult> findJokes(@RequestParam("searchKey") String searchKey){
        log.info("Inside findJokes method of JokesAndPeopleController");
        return ResponseEntity.status(HttpStatus.OK).body(jokesAndPeopleService.searchJoke(searchKey));
    }

    @GetMapping("/search/people")
    public ResponseEntity<OperationResult> findPeople(@RequestParam("searchKey") String searchKey){
        log.info("Inside findPeople method of JokesAndPeopleController");
        return ResponseEntity.status(HttpStatus.OK).body(jokesAndPeopleService.searchPeople(searchKey));
    }

    @GetMapping("/search")
    public ResponseEntity<OperationResult> searchJokeOrPerson(@RequestParam("searchKey") String searchKey){
        log.info("Inside searchJokeOrPerson method of JokesAndPeopleController");
        return ResponseEntity.status(HttpStatus.OK).body(jokesAndPeopleService.searchJokeOrPerson(searchKey));
    }
}
