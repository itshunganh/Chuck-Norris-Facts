package com.hap.hw4.models;
import javax.persistence.*;

@Entity
@Table(name = "cn")

public class API {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "quotes")
    private String quotes;
    
    public API() {
    }

    public API(String id, String quotes) {
        this.id = id;
        this.quotes = quotes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

}