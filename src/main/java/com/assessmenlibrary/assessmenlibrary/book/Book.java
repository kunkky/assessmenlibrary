package com.assessmenlibrary.assessmenlibrary.book;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Book {

        @Id
        @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
        )
        @GeneratedValue(
            strategy= GenerationType.SEQUENCE,
            generator="book_sequence"
        )

    private Long id;
    private String name;
    private String author;
    private String category;
    private String status;
    private Integer copy;
    private LocalDate dateadded;

    public Book(){
    
    }

    public Book( Long id, String name, String author, String category, String status, Integer copy, LocalDate dateadded){
        this.id=id;
        this.name=name;
        this.author=author;
        this.category=category;
        this.status=status;
        this.copy=copy;
        this.dateadded=dateadded;
    }
    //create constructor
    public Book( String name, String author, String category, String status, Integer copy, LocalDate dateadded){
        this.name=name;
        this.author=author;
        this.category=category;
        this.status=status;
        this.copy=copy;
        this.dateadded=dateadded;
    }

    public Book( Long id){
        this.id=id;
    }


    //set and get all field
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    //set and get  author
       public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author=author;
    }
    //set and get caategory
       public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category=category;
    }
    
        //set and get status
   public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }

    //set and get copy
   public Integer getCopy(){
        return copy;
    }
    public void setCopy(Integer copy){
        this.copy=copy;
    }
        //set and get dateadded
   public LocalDate getDateadded(){
        return dateadded;
    }
    public void setDateadded(LocalDate dateadded){
        this.dateadded=dateadded;
    }

        @Override
        public String toString(){
            return "Book{"+"id="+id+", name="+name+", author="+author+", category="+category+", status="+status+", copy="+copy+", dateadded="+dateadded+"}";
        }


}
