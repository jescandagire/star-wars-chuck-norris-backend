package com.zatecexercise.zatectechnical.service;

import com.zatecexercise.zatectechnical.config.OperationResult;
import com.zatecexercise.zatectechnical.dataMapper.PeopleAndJokeSearchResultMapper;
import com.zatecexercise.zatectechnical.dataMapper.PeopleSearchResultMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
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

    public OperationResult searchJokeOrPerson(String searchKey) {
        log.info("Inside searchJokeOrPerson method of JokesAndPeopleService");

        PeopleAndJokeSearchResultMapper resultMapper = new PeopleAndJokeSearchResultMapper();

        Map<String, String> params = new HashMap<>();
        params.put("search", searchKey);
        params.put("query", searchKey);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Object> searchResult;

        searchResult = restTemplate.exchange("https://api.chucknorris.io/jokes/search?query={query}", HttpMethod.GET, requestEntity, Object.class, params);
        Object jokeSearchResult = searchResult.getBody();
        searchResult = restTemplate.exchange("https://swapi.dev/api/people/?search={search}", HttpMethod.GET, requestEntity, Object.class, params);

        LinkedHashMap peopleSearchResult = (LinkedHashMap) searchResult.getBody();

        if((int) peopleSearchResult.get("count") == 0){
            resultMapper.setApiName("Jokes api");
            resultMapper.setResult(jokeSearchResult);
        }
        else{
            resultMapper.setApiName("People api");
            resultMapper.setResult(peopleSearchResult);
        }

        operationResult = new OperationResult(OperationResult.OPERATION_SUCCESSFUL_MESSAGE, resultMapper);
        return operationResult;
    }
}
