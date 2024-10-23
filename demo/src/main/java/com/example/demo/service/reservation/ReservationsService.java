package com.example.demo.service.reservation;

import com.example.demo.model.reservation.Reservation;

import java.util.List;

public interface ReservationsService {
    Reservation createReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long id);
    Reservation updateReservation(Long id, Reservation reservation);
    void deleteReservation(Long id);

    Reservation getReservationById(String id);

    Reservation updateReservation(String id, Reservation reservation);

    void deleteReservation(String id);

    List<Reservation> getReservationsByUserId(String userId);
}