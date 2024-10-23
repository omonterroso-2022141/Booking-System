package com.example.demo.service.reservation;

import com.example.demo.repository.ReservationRepository;
import com.example.demo.model.reservation.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationsServiceMap implements ReservationsService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return null;
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        return null;
    }

    @Override
    public void deleteReservation(Long id) {

    }

    @Override
    public Reservation getReservationById(String id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation updateReservation(String id, Reservation reservation) {
        if (reservationRepository.existsById(id)) {
            reservation.setId(id);  // Aseguramos que el ID es correcto
            return reservationRepository.save(reservation);
        }
        return null;
    }

    @Override
    public void deleteReservation(String id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> getReservationsByUserId(String userId) {
        return reservationRepository.findByUserId(userId);
    }
}