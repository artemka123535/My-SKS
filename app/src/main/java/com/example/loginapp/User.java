package com.example.loginapp;

public class User {
    public String id, name, surname, email, password, sex, birth, patronymic;

    public User() {

    }


    public User(String id, String name, String surname, String email, String password, String sex, String birth, String patronymic) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.birth = birth;
        this.patronymic = patronymic;
    }
}
