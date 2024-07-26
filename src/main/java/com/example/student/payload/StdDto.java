package com.example.student.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StdDto<T> {
    private List<StudentDto> dto;
    private int pageNo;
    private int pageSize;

}
