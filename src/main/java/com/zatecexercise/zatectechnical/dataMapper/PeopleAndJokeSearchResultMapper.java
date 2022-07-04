package com.zatecexercise.zatectechnical.dataMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleAndJokeSearchResultMapper {

    private String apiName;
    private Object result;
}
