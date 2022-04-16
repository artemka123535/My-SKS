package com.example.loginapp;

public class User {
    public String id, name, surname, email, password, sex, birth, patronymic, code, codeexist, schet;
    public  int pin;

    public User() {
    }

    public User(String id, String name, String surname, String email, String password, String sex, String birth, String patronymic, String code, String codeexist, String schet, int pin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.birth = birth;
        this.patronymic = patronymic;
        this.code = code;
        this.codeexist = codeexist;
        this.schet = schet;
        this.pin = pin;
    }
}
