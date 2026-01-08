package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.SeatDto;
import com.example.OnlineTicket.Excaption.ResourceNotFoundException;
import com.example.OnlineTicket.Repository.BusRepository;
import com.example.OnlineTicket.Repository.SeatRepository;
import com.example.OnlineTicket.model.Bus;
import com.example.OnlineTicket.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BusRepository busRepository;

    @Override
    public List<SeatDto> getAllSeats() {
        List<Seat> seats = seatRepository.findAll();
        return seats.stream()
                .map(seat -> new SeatDto(
                        seat.getId(),
                        seat.getBus().getBusNumber(),
                        seat.getSeatNumber(),
                        seat.getSeatType(),
                        seat.isAvailable(),
                        seat.getPrice()
                ))
                .toList();
    }

    @Override
    public SeatDto createSeat(SeatDto request)  {
        Bus bus = busRepository.findByBusNumber(request.getBusNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bus not found with number: " + request.getBusNumber())
                );
        Optional<Seat> existing = seatRepository.findBySeatNumberAndBus(request.getSeatNumber(),bus);
         if (existing.isPresent()){
             throw new RuntimeException("Seat already exist for this bus");
         }

        Seat seat = new Seat();
        seat.setSeatNumber(request.getSeatNumber());
        seat.setSeatType(request.getSeatType());
        seat.setPrice(request.getPrice());
        seat.setAvailable(request.isAvailable());
        seat.setBus(bus);

        Seat savedSeat  = seatRepository.save(seat);
        return new SeatDto(
                savedSeat.getId(),
                bus.getBusNumber(),
                savedSeat.getSeatNumber(),
                savedSeat.getSeatType(),
                savedSeat.isAvailable(),
                savedSeat.getPrice()
        );
    }

    @Override
    public SeatDto updateSeat(String busNumber, String seatNumber, SeatDto seatDto) {
        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(()-> new RuntimeException("Bus not found with number: " + busNumber));

        Seat seat = seatRepository.findBySeatNumberAndBus(seatNumber,bus)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Seat not found with number: " + seatNumber + " for bus " + busNumber
                ));

       seat.setSeatType(seatDto.getSeatType());
       seat.setPrice(seat.getPrice());
       seat.setAvailable(seat.isAvailable());

       Seat updatedSeat = seatRepository.save(seat);

        return new SeatDto(
                updatedSeat.getId(),
                bus.getBusNumber(),
                updatedSeat.getSeatNumber(),
                updatedSeat.getSeatType(),
                updatedSeat.isAvailable(),
                updatedSeat.getPrice()
        );

    }

    @Override
    public void deleteSeat(String busNumber, String seatNumber) {

        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));

        Seat seat = seatRepository.findBySeatNumberAndBus(seatNumber, bus)
                .orElseThrow(() -> new ResourceNotFoundException("Seat not found"));

        seatRepository.delete(seat);
    }


    @Override
    public List<SeatDto> getSeatsByBus(String busNumber) {
        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(() -> new RuntimeException("Bus not found: " + busNumber));

        return seatRepository.findByBus(bus)
                .stream()
                .map(seat -> new SeatDto(
                        seat.getId(),
                        bus.getBusNumber(),
                        seat.getSeatNumber(),
                        seat.getSeatType(),
                        seat.isAvailable(),
                        seat.getPrice()
                ))
                .toList();
    }

}

