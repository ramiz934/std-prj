package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.exception.ResourceNotFound;
import com.example.student.payload.StdDto;
import com.example.student.payload.StudentDto;
import com.example.student.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentDto createStudent(StudentDto studentDto){
       Student student=mapToEntity(studentDto);
        Student saved = studentRepository.save(student);
        StudentDto dto = mapToDto(saved);
       dto.setMessage("Registration saved");
        return dto;
    }

    @Override
    public void deleteStudent(long id) {
           studentRepository.deleteById(id);
    }

    @Override
    public StudentDto getStudent(long id) {
        
        return null;
    }

    @Override
    public StudentDto updateStudent(long id, StudentDto studentDto) {
        Optional<Student> byId = studentRepository.findById(id);
        Student student = byId.get();
        student.setName(studentDto.getName());
        student.setClas(studentDto.getClas());
        student.setCity(studentDto.getCity());
        student.setMarks(studentDto.getMarks());
        Student saved = studentRepository.save(student);
        StudentDto dto = mapToDto(saved);
        return dto;
    }

    @Override
    public StudentDto getStudentById(long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Student details is not found for the id:" + id));
        StudentDto dto = mapToDto(student);
        return dto;
    }

    @Override
    public StdDto<StudentDto> getAllStudent(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(Sort.Direction.ASC,sortBy) : Sort.by(Sort.Direction.DESC,sortBy);
        Pageable page=PageRequest.of(pageNo,pageSize,sort);
        Page<Student> all = studentRepository.findAll(page);
        List<Student> student = all.getContent();
        //List<Student> all = studentRepository.findAll();
        List<StudentDto> dto = student.stream().map(s->mapToDto(s)).collect(Collectors.toList());
//        System.out.println(page.getPageNumber());
//        System.out.println(page.getPageSize());
        StdDto stdDto=new StdDto();
        stdDto.setDto(dto);
        stdDto.setPageSize(page.getPageSize());
        stdDto.setPageNo(page.getPageNumber());
        return stdDto;

    }

    Student mapToEntity(StudentDto dto){
        Student entity= new Student();
        entity.setName(dto.getName());
        entity.setClas(dto.getClas());
        entity.setCity(dto.getCity());
        entity.setMarks(dto.getMarks());
        return entity;
    }
    StudentDto mapToDto(Student student) {
        StudentDto dto= new StudentDto();
        dto.setRoll_no(student.getRoll_no());
        dto.setName(student.getName());
        dto.setClas(student.getClas());
        dto.setCity(student.getCity());
        dto.setCity(student.getCity());
        dto.setMarks(student.getMarks());
        return dto;
    }
}
