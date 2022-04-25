package com.example.loginapp;

public class User {
    public String id, name, surname, email, password, sex, birth, patronymic, code, codeexist, schet, schet1, address, address1;
    public float cash, cash1, pokaz;
    public int GVS, people;


    public User() {
    }

    public User(String id, String name, String surname, String email, String password, String sex, String birth, String patronymic, String code, String codeexist, String schet, String schet1, String address, float cash, String address1,float cash1, int GVS, int people, float pokaz) {
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
        this.schet1 = schet1;
        this.address =address;
        this.cash = cash;
        this.address1 =address1;
        this.cash1 = cash1;
        this.GVS = GVS;
        this.people = people;
        this.pokaz = pokaz;
    }
}
