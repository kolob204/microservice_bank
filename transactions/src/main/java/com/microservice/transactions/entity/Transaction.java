package com.microservice.transactions.entity;


import java.sql.Timestamp;

public class Transaction {

    private long id;
    private long type;          //тип транзакции  (код типа из справочника)
    private boolean direction;  //направление транзакции (оригинал /отмена)
    private long source;        //код системы из справочника
    private long summ;          // сумма транзакции
    private String currency;    //валюта
    private Timestamp time;          // дата транзакции


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public long getSource() {
        return source;
    }

    public void setSource(long source) {
        this.source = source;
    }

    public long getSumm() {
        return summ;
    }

    public void setSumm(long summ) {
        this.summ = summ;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
