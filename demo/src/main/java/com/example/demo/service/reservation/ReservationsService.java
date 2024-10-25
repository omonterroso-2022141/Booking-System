package com.example.demo.service.reservation;

import com.example.demo.model.Reservation;
import com.example.demo.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationsService {

    private final ReservationRepository reservationRepository;

    public ReservationsService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation getReservation(String id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation updateReservation(String id, Reservation reservation) {
        Reservation reservationExist = reservationRepository.findById(id).orElse(null);
        if (reservationExist != null) {
            reservationExist.setDate(reservation.getDate());
            reservationExist.setDescription(reservation.getDescription());
            return reservationRepository.save(reservationExist);
        }
        return null;
    }

    public void deleteReservation(String id) {
        reservationRepository.deleteById(id);
    }
}
