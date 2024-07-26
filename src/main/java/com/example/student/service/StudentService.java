package com.example.student.service;

import com.example.student.payload.StdDto;
import com.example.student.payload.StudentDto;

import java.util.List;

public interface StudentService {

    public StudentDto createStudent(StudentDto studentDto);
    public void deleteStudent(long id);
    public StudentDto getStudent(long id);

    public StudentDto updateStudent(long id, StudentDto studentDto);

    public StudentDto getStudentById(long id);

    StdDto<StudentDto> getAllStudent(int pageNo, int pageSize, String sortBy, String sortDir);
}
