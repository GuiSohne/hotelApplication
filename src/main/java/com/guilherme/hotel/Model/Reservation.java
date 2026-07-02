package com.guilherme.hotel.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Reservation {
    private long id;
    private int guestid;
    private long roomid;
    private LocalDate checkin;
    private LocalDate checkout;
    private BigDecimal totalamount;


    //construtores
    public Reservation(){}

    public Reservation(long id, int guestid, int roomid, LocalDate checkin, LocalDate checkout, BigDecimal totalamount ){
        this.id = id;
        this.guestid = guestid;
        this.roomid = roomid;
        this.checkin = checkin;
        this.checkout = checkout;
        this.totalamount = totalamount;
    }


    //Getters AND Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGuestid() {
        return guestid;
    }

    public void setGuestid(int guestid) {
        this.guestid = guestid;
    }

    public long getRoomid() {
        return roomid;
    }

    public void setRoomid(long roomid) {
        this.roomid = roomid;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }





    @Override
    public String toString() {
        return "Reserva #" + id +
                " | Hóspede: " + guestid +
                " | Quarto: " + roomid +
                " | Check-in: " + checkin +
                " | Check-out: " + checkout;
    }
}
