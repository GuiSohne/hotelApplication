package com.guilherme.hotel.Model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Rooms {
    private long id;
    private int number;
    private String type;
    private Double daily_rate;
    private String status;

    public Rooms(long id, int number, String type, long price, String status) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.daily_rate = daily_rate;
        this.status = status;
    }

    public Rooms() {

    }
}
