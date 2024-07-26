package com.example.student.controller;

import com.example.student.entity.Student;
import com.example.student.payload.StdDto;
import com.example.student.payload.StudentDto;
import com.example.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    //http://localhost:8080/api/v1/student
    @PostMapping
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentDto studentDto, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.OK);
        }
        StudentDto std = studentService.createStudent(studentDto);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<String> removeStudentById(@RequestParam long id){
              studentService.deleteStudent(id);
              return new ResponseEntity<>("Student deleted", HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@RequestParam long id,
                                                    @RequestBody StudentDto studentDto){
        StudentDto dto = studentService.updateStudent(id, studentDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/byid")
    public ResponseEntity<StudentDto> getStudentById(@RequestParam long id){
        StudentDto dtos = studentService.getStudentById(id);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping()
    public ResponseEntity<StdDto<StudentDto>> getAllStudent(
            @RequestParam(name="pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(name="pageSize",defaultValue = "2",required = false)int pageSize,
            @RequestParam(name="sortBy",defaultValue = "name",required = false)String sortBy,
            @RequestParam(name="sortDir",defaultValue = "asc",required = false)String sortDir
    ){
        StdDto<StudentDto> dto = studentService.getAllStudent(pageNo, pageSize,sortBy,sortDir);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
