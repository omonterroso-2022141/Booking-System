package com.example.demo.service.reservation;

import com.example.demo.repository.reservation.Reservation;

import java.util.List;

public interface ReservationsService {
    Reservation createReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long id);
    Reservation updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);
}