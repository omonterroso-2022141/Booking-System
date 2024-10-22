package com.example.demo.service.reservation;

import com.example.demo.repository.reservation.Reservation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReservationsServiceMap implements ReservationsService {
    private HashMap<Long, Reservation> reservations = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public Reservation createReservation(Reservation reservation) {
        reservation.setId(currentId++);
        reservations.put(reservation.getId(), reservation);
        return reservation;
    }

    @Override
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations.values());
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservations.get(id);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        reservations.put(id, reservation);
        return reservation;
    }

    @Override
    public void deleteReservation(Long id) {
        reservations.remove(id);
    }
}