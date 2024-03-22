package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Student {
    private Integer id ;
    private String name ;
    private  int age;
    private Integer book_id ;

    public Student(String name , int age){
        this.name = name;
        this.age = age;
    }
    public Student(Integer id ,String name , int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
