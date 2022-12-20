package ru.alishev.springcourse.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Book {
    private int book_id;

    private Integer person_id;

    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Author should not be empty")
    private String author;
    @Max(value = 2022, message = "Year should be less than 2022")
    private int year_of_writing;

    public Book() {
    }

    public Book(int book_id, String name, String author, int year_of_writing) {
        this.book_id = book_id;
        this.name = name;
        this.author = author;
        this.year_of_writing = year_of_writing;
        this.person_id = null;
    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear_of_writing() {
        return year_of_writing;
    }

    public void setYear_of_writing(int year_of_writing) {
        this.year_of_writing = year_of_writing;
    }
}
