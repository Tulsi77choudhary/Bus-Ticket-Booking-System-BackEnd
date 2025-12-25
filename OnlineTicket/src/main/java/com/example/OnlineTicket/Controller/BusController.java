package com.example.OnlineTicket.Controller;

import com.example.OnlineTicket.DTO.BusDTO;
import com.example.OnlineTicket.DTO.BusRequest;
import com.example.OnlineTicket.DTO.BusResponseDTO;
import com.example.OnlineTicket.DTO.SeatDto;
import com.example.OnlineTicket.Service.BusService;
import com.example.OnlineTicket.Service.SeatService;
import com.example.OnlineTicket.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin(origins = "http://localhost:5173")
public class BusController {

    @Autowired
    private BusService busService;
    @Autowired
    private SeatService seatService;

    @PostMapping
    public Bus addBus(@RequestBody Bus bus) {
        return busService.addBus(bus);
    }

    @GetMapping
    public List<BusDTO> getAllBuses(
            @RequestParam(required = false) String source,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) String busNumber) {
        List<Bus> buses = busService.getAllBuses(source, destination, busNumber);
        return buses.stream()
                .map(busService::mapToBusDto)
                .toList();
    }


    @PostMapping("/search")
    public List<BusResponseDTO> searchBuses(@RequestBody BusRequest request) {
        List<Bus> buses = busService.searchBuses(request.getBusNumber(), request.getSource(), request.getDestination());

        return buses.stream()
                .map(bus -> new BusResponseDTO(
                        bus.getBusNumber(),
                        bus.getSource(),
                        bus.getDestination(),
                        bus.getDate(),
                        bus.getTime(),
                        bus.getTotalSeats(),

                        bus.getSeats().stream()
                                .map(seat -> new SeatDto(
                                        seat.getSeatNumber(),
                                        seat.getSeatType().name(),
                                        seat.getPrice(),
                                        seat.getBus().getBusNumber()

                                ))
                                .toList()
                ))
                .collect(Collectors.toList());
    }

    @PutMapping("/{busNumber}")
    public ResponseEntity<BusDTO> updateBus(@PathVariable String busNumber, @RequestBody BusDTO busDTO) {
        BusDTO updated = busService.updateBus(busNumber, busDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBus(@PathVariable Long id) {
        try {
            busService.deleteBus(id);
            return ResponseEntity.ok("Bus deleted successfully with ID: " + id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bus not found with ID: " + id);
        }
    }

}
