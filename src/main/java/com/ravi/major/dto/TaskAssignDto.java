package com.ravi.major.dto;
import lombok.Data;


@Data
public class TaskAssignDto {

    private long id;
    private String title;
    private int emp_id;
    private String date;
    private String description;
 }
