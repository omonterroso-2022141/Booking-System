package com.example.demo.controller.reservation;

import com.example.demo.model.reservation.Reservation;
import com.example.demo.service.reservation.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    @Autowired
    private ReservationsService reservationsService;

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationsService.createReservation(reservation);
    }

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationsService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable String id) {
        return reservationsService.getReservationById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Reservation> getReservationsByUserId(@PathVariable String userId) {
        return reservationsService.getReservationsByUserId(userId);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable String id, @RequestBody Reservation reservation) {
        return reservationsService.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable String id) {
        reservationsService.deleteReservation(id);
    }
}
