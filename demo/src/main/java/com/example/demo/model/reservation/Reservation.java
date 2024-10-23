package com.example.demo.model.reservation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "reservations")
public class Reservation {
    @Id
    private String  id;
    private String  userId;
    private LocalDate reservationDate;

    public Reservation(String id, LocalDate reservationDate, String userId) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
}
