package com.example.demo.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name can't be empty")
    @Pattern(regexp = "[a-zA-Zа-яА-ЯёЁ\\s]+", message = "Name must contain only letters")
    @Column
    private String name;

    @NotEmpty(message = "Last name can't be empty")
    @Pattern(regexp = "[a-zA-Zа-яА-ЯёЁ\\s]+", message = "Last name must contain only letters")
    @Column
    private String lastName;

    @Min(value = 1, message = "Age should be greater than 0")
    @Column
    private int age;

    public User(Long id, String name, String lastName, int age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public User() {

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
