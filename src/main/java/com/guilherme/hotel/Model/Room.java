package com.guilherme.hotel.Model;

public class Room {
    private long id;
    private int number;
    private String type;
    private Double daily_rate;
    private String status;

    //Construtores
    public Room(){}

    public Room(long id, int number, String type, Double daily_rate, String status) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.daily_rate = daily_rate;
        this.status = status;
    }

    //Getters AND Setters
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getDaily_rate() {
        return daily_rate;
    }

    public void setDaily_rate(Double daily_rate) {
        this.daily_rate = daily_rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //Como o objeto irá aparecer
    @Override
    public String toString() {
        return "Quarto " + number;
    }
}


