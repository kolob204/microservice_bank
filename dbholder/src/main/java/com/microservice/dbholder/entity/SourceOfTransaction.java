package com.microservice.dbholder.entity;

import javax.persistence.*;

@Entity  //  СУЩНОСТЬ. связь с БД
@Table(name="sourceoft")
public class SourceOfTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String source;

    public SourceOfTransaction() {
    }

    public SourceOfTransaction(String source) {
        this.source = source;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
