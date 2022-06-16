package com.zatecexercise.zatectechnical.dataMapper;

import com.zatecexercise.zatectechnical.entity.PeopleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleSearchResultMapper<T> {
    private int count;
    private String next;
    private String previous;
    private PeopleEntity[] results;
}
