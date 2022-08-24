package ru.Vladislav.project7Boot.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name="Person")
public class Person {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Pattern(regexp ="[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+",message = "Вы неправильно ввели ФИО")
    @Column(name="fio")
    private String fio;
    @Min(value=1900,message = "Вы ввели слишком ранний год")
    @Max(value = 2022,message="Данный год ещё не наступил")
    @Column(name="year_born")
    private int year_born;

    @OneToMany(mappedBy = "person")
    private List<Book> bookList;

    public Person(){

    }

    public Person(String fio, int year_born) {
        this.fio = fio;
        this.year_born = year_born;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }


    public String getFio() {
        return fio;
    }

    public int getYear_born() {
        return year_born;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setYear_born(int year_born) {
        this.year_born = year_born;
    }
}
