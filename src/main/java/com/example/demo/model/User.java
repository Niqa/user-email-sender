package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@TableGenerator(name = "userIdGenerator", initialValue = 0) //sprawdz initialValue
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "userIdGenerator") //żeby sam zarządzał id
    private Long id;
    @Size(min = 3, max = 10, message = "{user.name.info}")
    private String name;
    @Size(min = 3, max = 20, message = "{user.surname.info}")
    private String surname;
    @Min(value = 18, message = "Pracownik musi być pełnoletni!")
    @Max(value = 65, message = "Pracownik powinien przejść na emeryturę.")
    private Integer age;
    private String country;
    @Pattern(regexp = "^(.+)@(.+)$")
    private String email;

    public User(){}

    public User(Long id, String name, String surname, Integer age, String country, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.country = country;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
