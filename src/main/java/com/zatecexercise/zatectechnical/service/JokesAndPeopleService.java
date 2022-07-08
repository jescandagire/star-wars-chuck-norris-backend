package com.zatecexercise.zatectechnical.service;

import com.zatecexercise.zatectechnical.config.OperationResult;
import com.zatecexercise.zatectechnical.dataMapper.PeopleAndJokeSearchResultMapper;
import com.zatecexercise.zatectechnical.dataMapper.PeopleSearchResultMapper;
import com.zatecexercise.zatectechnical.exception.SearchKeyLengthException;
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

        if(searchKey.length() < 3){
            throw new SearchKeyLengthException("The length of the search key should be at least 3 characters");
        }

        PeopleAndJokeSearchResultMapper resultMapper = new PeopleAndJokeSearchResultMapper();

        Map<String, String> params = new HashMap<>();
        params.put("search", searchKey);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Object> searchResult;

        searchResult = restTemplate.exchange("https://api.chucknorris.io/jokes/search?query={search}", HttpMethod.GET, requestEntity, Object.class, params);
        Object jokeSearchResult = searchResult.getBody();
        searchResult = restTemplate.exchange("https://swapi.dev/api/people/?search={search}", HttpMethod.GET, requestEntity, Object.class, params);

        LinkedHashMap peopleSearchResult = (LinkedHashMap) searchResult.getBody();

        int peopleCount = (int) peopleSearchResult.get("count");

        if( peopleCount == 0){
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

    public OperationResult getJokeCategoryDetails(String jokeName){
        log.info("Inside getJokeCategoryDetails method of JokesAndPeopleService");

        if(jokeName.length() < 3){
            throw new SearchKeyLengthException("The length of the search key should be at least 3 characters");
        }
        Map<String, String> params = new HashMap<>();
        params.put("search", jokeName);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Object> result = restTemplate.exchange("https://api.chucknorris.io/jokes/random?category={search}", HttpMethod.GET, requestEntity, Object.class, params);

        Object categoryDetails = result.getBody();

        return new OperationResult(OperationResult.OPERATION_SUCCESSFUL_MESSAGE, categoryDetails);
    }
}
