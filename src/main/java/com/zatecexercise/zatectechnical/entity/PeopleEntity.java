package com.zatecexercise.zatectechnical.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeopleEntity {
    public String name;
    public String height;
    public String mass;
    public String hair_color;
    public String skin_color;
    public String eye_color;
    public String birth_year;
    public String gender;
    public String homeworld;
    public String[] films;
    public String[] species;
    public String[] vehicles;
    public String[] starships;
    public String url;
    public LocalDateTime created;
    public LocalDateTime edited;
}
