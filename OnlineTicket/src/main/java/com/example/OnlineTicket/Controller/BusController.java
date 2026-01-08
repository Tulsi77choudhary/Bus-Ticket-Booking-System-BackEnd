package com.example.OnlineTicket.Controller;


import com.example.OnlineTicket.DTO.BusRequest;
import com.example.OnlineTicket.DTO.BusResponseDTO;
import com.example.OnlineTicket.DTO.CreateBus;
import com.example.OnlineTicket.Excaption.BusException;
import com.example.OnlineTicket.Service.BusService;
import com.example.OnlineTicket.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin(origins = "http://localhost:5173")
public class BusController {
    @Autowired
    private BusService busService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/bus")
    public ResponseEntity<Bus> createBus(@RequestBody CreateBus request) {
        Bus bus = busService.createBus(request);
        return ResponseEntity.ok(bus);
    }

    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @PostMapping("/search")
    public ResponseEntity<List<Bus>> searchBuses(@RequestBody BusRequest request) throws BusException {

        List<Bus> bus = busService.searchBuses(request);

        return new  ResponseEntity<>(bus,HttpStatus.OK);

    }
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping
    public ResponseEntity<List<Bus>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        return ResponseEntity.ok(buses);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{busNumber}")
    public ResponseEntity<BusResponseDTO> updateBus(@PathVariable String busNumber, @RequestBody CreateBus busDTO) {
        BusResponseDTO updated = busService.updateBus(busNumber, busDTO);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable Long id){
        busService.cancelBus(id);
        return ResponseEntity.ok("Bus cancelled successfully with ID: " + id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBus(@PathVariable String busNumber) {
        busService.deleteBus(busNumber);
        return ResponseEntity.ok("Bus deleted successfully with ID: " + busNumber);
    }
}
