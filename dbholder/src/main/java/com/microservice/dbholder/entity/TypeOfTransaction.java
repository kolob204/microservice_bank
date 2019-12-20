package com.microservice.dbholder.entity;


import javax.persistence.*;

@Entity  //  СУЩНОСТЬ. связь с БД
@Table(name="typeoft")
public class TypeOfTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String type; //тип транзакции

    public TypeOfTransaction() {
    }

    public TypeOfTransaction(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
