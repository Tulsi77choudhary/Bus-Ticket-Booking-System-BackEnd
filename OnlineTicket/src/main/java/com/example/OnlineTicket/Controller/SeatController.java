package com.example.OnlineTicket.Controller;

import com.example.OnlineTicket.DTO.SeatDto;
import com.example.OnlineTicket.Excaption.BusException;
import com.example.OnlineTicket.Excaption.SeatException;
import com.example.OnlineTicket.Service.SeatService;
import com.example.OnlineTicket.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/seat")
@CrossOrigin(origins = "http://localhost:5173")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/seats")
    public ResponseEntity<SeatDto> createSeat(@RequestBody SeatDto request)
            throws SeatException, BusException {

        SeatDto savedSeat = seatService.createSeat(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSeat);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SeatDto>> getAllSeats() throws SeatException, BusException {
        List<SeatDto> seats = seatService.getAllSeats();
        return new ResponseEntity<>(seats,HttpStatus.OK);
    }

    @GetMapping("/bus/{busNumber}")
    public ResponseEntity<List<SeatDto>> getSeatsByBus(@PathVariable String busNumber) throws SeatException, BusException {
        List<SeatDto> seats = seatService.getSeatsByBus(busNumber);
        return new ResponseEntity<>(seats,HttpStatus.OK) ;
    }

    @PutMapping("/{busNumber}/{seatNumber}")
    public ResponseEntity<SeatDto> updateSeat(@PathVariable String busNumber,@PathVariable String seatNumber, @RequestBody SeatDto seatDto) {
        SeatDto updated = seatService.updateSeat(busNumber,seatNumber,seatDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{busNumber}/{seatNumber}")
    public ResponseEntity<Void> deleteSeat(
            @PathVariable String busNumber,
            @PathVariable String seatNumber) {

        seatService.deleteSeat(busNumber, seatNumber);
        return ResponseEntity.noContent().build();
    }
}

