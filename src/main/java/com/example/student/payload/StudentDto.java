package com.example.student.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class StudentDto {


        private long roll_no;

        @Size(min=2,max=10,message = "Name Should be more than 2 characters")
        private String name;

        @NotNull(message = "class shouldn't be left blank")
        private String clas;

        @NotNull(message = "city shouldn't be left blank")
        private String city;

        private int marks;

        private String message;


}
