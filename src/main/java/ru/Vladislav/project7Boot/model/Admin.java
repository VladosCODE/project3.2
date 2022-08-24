package ru.Vladislav.project7Boot.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="Admin")
public class Admin {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="login")
    @Size(min=5,max=100,message = "Login must have length between 5 and 100")
    private String login;

    @Column(name="name")
    @Pattern(regexp ="[A-Z]\\w+",message = "Неправильный формат имени")
    @NotEmpty(message = "Вы не ввели имя")
    private String name;

    @Column(name="surname")
    @Pattern(regexp ="[A-Z]\\w+",message = "Неправильный формат фамилии")
    @NotEmpty(message = "Вы не ввели фамилию")
    private String surname;
    @Column(name="password")
    private String password;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name="role")
    private String role;

    public Admin(){}

    public Admin(String login, String name, String surname, String password) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
