package com.microservice.transactions.entity;


public class SourceOfTransaction {

    private long id;

    private String source; //тип транзакции

    public SourceOfTransaction() {
    }

    public SourceOfTransaction(long id, String source) {
        this.id = id;
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
