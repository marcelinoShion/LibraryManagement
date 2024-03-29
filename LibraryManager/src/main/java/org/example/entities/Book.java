package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Book {
    private Integer id ;
    private String name;
    private Integer student_id;
    public Book(String name){
        this.name = name ;
    }

    public Book(Integer id , String name){
        this.id = id ;
        this.name = name;
    }

}
