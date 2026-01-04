package com.example.OnlineTicket.Controller;

import com.example.OnlineTicket.DTO.BusDTO;
import com.example.OnlineTicket.DTO.BusRequest;
import com.example.OnlineTicket.DTO.BusResponseDTO;
import com.example.OnlineTicket.Excaption.ResourceNotFoundException;
import com.example.OnlineTicket.Service.BusService;
import com.example.OnlineTicket.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin(origins = "http://localhost:5173")
public class BusController {
    @Autowired
    private BusService busService;
    @PostMapping("/bus")
    public ResponseEntity<Bus> addBus(@RequestBody BusDTO busdto) {
        Bus busSave = busService.addBus(busdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(busSave);
    }
    @PostMapping("/search")
    public ResponseEntity<List<BusResponseDTO>> searchBuses(@RequestBody BusRequest request) {

        List<Bus> buses = busService.searchBuses(
                request.getBusNumber(),
                request.getSource(),
                request.getDestination()
        );
        if (buses.isEmpty()) {
            throw new ResourceNotFoundException("No buses found for given search criteria");
        }

        List<BusResponseDTO> response = buses.stream()
                .map(busService::mapToBusResponseDTO)
                .toList();

        return new  ResponseEntity<>(response,HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<BusResponseDTO>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();

        List<BusResponseDTO> response = buses.stream()
                .map(busService::mapToBusResponseDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{busNumber}")
    public ResponseEntity<BusResponseDTO> updateBus(@PathVariable String busNumber, @RequestBody BusDTO busDTO) {
        BusResponseDTO updated = busService.updateBus(busNumber, busDTO);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
        return ResponseEntity.ok("Bus deleted successfully with ID: " + id);
    }
}
