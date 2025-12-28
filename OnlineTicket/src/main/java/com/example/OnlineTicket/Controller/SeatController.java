package com.example.OnlineTicket.Controller;

import com.example.OnlineTicket.DTO.SeatDto;
import com.example.OnlineTicket.DTO.SeatSelectionRequest;
import com.example.OnlineTicket.Repository.BusRepository;
import com.example.OnlineTicket.Repository.SeatRepository;
import com.example.OnlineTicket.Service.SeatService;
import com.example.OnlineTicket.model.Bus;
import com.example.OnlineTicket.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/seats")
@CrossOrigin(origins = "http://localhost:5173")
public class SeatController {

    @Autowired
    private SeatService seatService;
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BusRepository busRepository;
    @GetMapping
    public List<SeatDto> getAllSeats() {
        return seatService.getAllSeat();
    }

    @GetMapping("/bus/{busNumber}")
    public ResponseEntity<List<SeatDto>> getSeatsByBus(@PathVariable String busNumber) {
        List<SeatDto> seats = seatService.getSeatsByBus(busNumber);
        return ResponseEntity.ok(seats);
    }
    @PostMapping("/select")
    public ResponseEntity<List<SeatDto>> selectSeats(
            @RequestBody SeatSelectionRequest request) {

        return ResponseEntity.ok(
                seatService.selectSeats(
                        request.getBusNumber(),
                        request.getSeatNumbers())
        );
    }
    @PostMapping
    public ResponseEntity<SeatDto> createSeat(@RequestBody SeatDto seatDto) {
        SeatDto created = seatService.createSeat(seatDto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{busNumber}/{seatNumber}")
    public ResponseEntity<SeatDto> updateSeat(@PathVariable String busNumber,@PathVariable String seatNumber, @RequestBody SeatDto seatDto) {
        SeatDto updated = seatService.updateSeat(busNumber,seatNumber, seatDto);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{seatNumber}")
    public ResponseEntity<Void> deleteSeat(@PathVariable String seatNumber) {
        seatService.deleteSeat(seatNumber);
        return ResponseEntity.noContent().build();
    }
}

