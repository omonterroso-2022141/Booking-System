package com.example.demo.controller.reservation;

import com.example.demo.model.Reservation;
import com.example.demo.service.reservation.ReservationsService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private final ReservationsService reservationsService;

    public ReservationsController(ReservationsService reservationsService) {
        this.reservationsService = reservationsService;
    }

    @PostMapping("/create")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationsService.createReservation(reservation);
    }

    @GetMapping("/find-by/{id}")
    public Reservation getReservation(@PathVariable String id) {
        return reservationsService.getReservation(id);
    }

    @GetMapping("/list")
    public List<Reservation> getAllReservations() {
        return reservationsService.getAllReservations();
    }

    @PutMapping("/update/{id}")
    public Reservation updateReservation(@PathVariable String id, @RequestBody Reservation reservation) {
        return reservationsService.updateReservation(id, reservation);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReservation(@PathVariable String id) {
        reservationsService.deleteReservation(id);
    }
}
