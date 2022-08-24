package ru.Vladislav.project7Boot.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="Book")
public class Book {
    @Id
    @Column(name="id_book")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_book;
    @Size(min=2,max=30,message="Название книги не может быть меньше 2 символов")
    @Column(name="name")
    private String name;
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+",message = "Вы неправильно ввели ФИО автора")
    @Column(name="author")
    private String author;
    @Min(value=1800,message = "Вы ввели слишком ранний год")
    @Max(value = 2022,message="Данный год ещё не наступил")
    @Column(name="year_release")
    private int year_release;

    @Column(name="time")
    @Temporal(TemporalType.DATE)
    private Date time;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @ManyToOne
    @JoinColumn(name = "person_id",referencedColumnName = "id")
    private Person person;
    public Book(){

    }
    public Book(String name, String author, int year_release) {
        this.name = name;
        this.author = author;
        this.year_release = year_release;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getId_book() {
        return id_book;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear_release() {
        return year_release;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear_release(int year_release) {
        this.year_release = year_release;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id_book == book.id_book && year_release == book.year_release && Objects.equals(name, book.name) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_book, name, author, year_release);
    }
}
