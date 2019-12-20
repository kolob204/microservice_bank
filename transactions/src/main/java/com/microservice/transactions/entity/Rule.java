package com.microservice.transactions.entity;

import java.sql.Timestamp;

public class Rule {

    private long id;
    private String type; //тип(равно)
    private String direction;  //направление(равно)
    private String source;   //Источник(равно)

    private String summ_compare;
    private String summ; //Сумма(больше/меньше/равно)

    private String action; //  действие, которое необходимо выполнить (Провести/отклонить)
    private String code_type_of_balance;//Код типа баланса

    private Timestamp start_Date;//дата начала
    private Timestamp end_Date;//дата окончания

    public Rule() {
    }

    public Rule(long id, String type, String direction, String source, String summ_compare, String summ, String action, String code_type_of_balance, Timestamp start_Date, Timestamp end_Date) {
        this.id = id;
        this.type = type;
        this.direction = direction;
        this.source = source;
        this.summ_compare = summ_compare;
        this.summ = summ;
        this.action = action;
        this.code_type_of_balance = code_type_of_balance;
        this.start_Date = start_Date;
        this.end_Date = end_Date;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSumm() {
        return summ;
    }

    public void setSumm(String summ) {
        this.summ = summ;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCode_type_of_balance() {
        return code_type_of_balance;
    }

    public void setCode_type_of_balance(String code_type_of_balance) {
        this.code_type_of_balance = code_type_of_balance;
    }

    public Timestamp getStart_Date() {
        return start_Date;
    }

    public void setStart_Date(Timestamp start_Date) {
        this.start_Date = start_Date;
    }

    public Timestamp getEnd_Date() {
        return end_Date;
    }

    public void setEnd_Date(Timestamp end_Date) {
        this.end_Date = end_Date;
    }

    public String getSumm_compare() {
        return summ_compare;
    }

    public void setSumm_compare(String summ_compare) {
        this.summ_compare = summ_compare;
    }
}
