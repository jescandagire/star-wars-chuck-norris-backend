package com.zatecexercise.zatectechnical.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JokesCategoryEntity {

    public String id;
    public String categories;
    public String icon_url;
    public String url;
    public String value;
    @JsonFormat(pattern = "2020-01-05 13:42:19.576875")
    public LocalDateTime created_at;
    @JsonFormat(pattern = "2020-01-05 13:42:19.576875")
    public LocalDateTime updated_at;
}
