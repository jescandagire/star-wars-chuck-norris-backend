package com.zatecexercise.zatectechnical.service;

import com.zatecexercise.zatectechnical.config.OperationResult;
import com.zatecexercise.zatectechnical.dataMapper.PeopleSearchResultMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JokesAndPeopleService {
    @Autowired
    RestTemplate restTemplate;

    OperationResult operationResult = null;

    public OperationResult findAllJokesCategories(){
        log.info("Inside findAllJokesCategories method of JokesAndPeopleService");
        String[] jokesCategories = restTemplate.getForObject("https://api.chucknorris.io/jokes/categories", String[].class);
        operationResult = new OperationResult(OperationResult.OPERATION_SUCCESSFUL_MESSAGE, jokesCategories);
        return operationResult;
    }

    public OperationResult findAllPeople(){
        log.info("Inside findAllPeople method of JokesAndPeopleService");

        Object peopleReturned = restTemplate.getForObject("https://swapi.dev/api/people", Object.class);
        operationResult = new OperationResult(OperationResult.OPERATION_SUCCESSFUL_MESSAGE, peopleReturned);
        return operationResult;
    }

    public OperationResult searchJoke(String searchKey){
        log.info("Inside searchJoke method of JokesAndPeopleService");
        Map<String, String> params = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);
        params.put("query", searchKey);
        ResponseEntity<Object> searchResult = restTemplate.exchange("https://api.chucknorris.io/jokes/search?query={query}", HttpMethod.GET, requestEntity, Object.class, params);
        operationResult = new OperationResult(OperationResult.OPERATION_SUCCESSFUL_MESSAGE, searchResult.getBody());
        return operationResult;
    }

    public OperationResult searchPeople(String searchKey){
        log.info("Inside searchPeople method of JokesAndPeopleService");
        Map<String, String> params = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);
        params.put("search", searchKey);
        ResponseEntity<Object> searchResult = restTemplate.exchange("https://swapi.dev/api/people/?search={search}", HttpMethod.GET, requestEntity, Object.class, params);
        operationResult = new OperationResult(OperationResult.OPERATION_SUCCESSFUL_MESSAGE, searchResult.getBody());
        return operationResult;
    }

    public OperationResult searchJokeOrPerson(String searchKey){
        log.info("Inside searchJokeOrPerson method of JokesAndPeopleService");
        Map<String, String> params = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);
        params.put("search", searchKey);

        ResponseEntity<Object> searchResult;

        searchResult = restTemplate.exchange("https://swapi.dev/api/people/?search={search}", HttpMethod.GET, requestEntity, Object.class, params);
        Object peopleSearchResult = searchResult.getBody();
        System.out.println(peopleSearchResult);
        operationResult = new OperationResult(OperationResult.OPERATION_SUCCESSFUL_MESSAGE, peopleSearchResult);
        return operationResult;
    }
}
