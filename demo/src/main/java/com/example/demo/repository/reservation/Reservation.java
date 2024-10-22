package com.example.demo.repository.reservation;

import java.time.LocalDate;

public class Reservation {
    private Long id;
    private Long userId;
    private LocalDate reservationDate;

    public Reservation(Long id, Long userId, LocalDate reservationDate) {
        this.id = id;
        this.userId = userId;
        this.reservationDate = reservationDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public LocalDate getReservationDate() { return reservationDate; }
    public void setReservationDate(LocalDate reservationDate) { this.reservationDate = reservationDate; }
}
