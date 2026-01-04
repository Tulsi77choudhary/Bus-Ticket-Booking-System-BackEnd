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
import java.util.stream.Collectors;

@Service
@Transactional
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BusRepository busRepository;

    private SeatDto mapToDto(Seat seat) {
        SeatDto dto = new SeatDto();
        dto.setId(seat.getId());
        dto.setSeatNumber(seat.getSeatNumber());
        dto.setSeatType(seat.getSeatType());
        dto.setAvailable(seat.isAvailable());
        dto.setBusNumber(seat.getBus().getBusNumber());
        dto.setPrice(seat.getPrice());

        return dto;
    }


    @Override
    public List<SeatDto> getAllSeat() {
        return seatRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SeatDto createSeat(SeatDto seatDto)  {
        Bus bus = busRepository.findByBusNumber(seatDto.getBusNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Bus not found with number: " + seatDto.getBusNumber())
                );
        Optional<Seat> existing = seatRepository.findBySeatNumberAndBus(seatDto.getSeatNumber(),bus);
         if (existing.isPresent()){
             throw new RuntimeException("Seat already exist for this bus");
         }

        Seat seat = new Seat();
        seat.setSeatNumber(seatDto.getSeatNumber());
        seat.setSeatType(seatDto.getSeatType());
        seat.setPrice(seatDto.getPrice());
        seat.setAvailable(seatDto.isAvailable());
        seat.setBus(bus);
        Seat saved = seatRepository.save(seat);
        return mapToDto(saved);
    }

    @Override
    public SeatDto updateSeat(String busNumber, String seatNumber, SeatDto seatDto) {
        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(()-> new RuntimeException("Bus not found with number: " + busNumber));

        Seat seat = seatRepository.findBySeatNumberAndBus(seatNumber,bus)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Seat not found with number: " + seatNumber + " for bus " + busNumber
                ));

        if(seatDto.getSeatNumber() != null)  seat.setSeatNumber(seatDto.getSeatNumber());
        if (seatDto.getSeatType() != null) seat.setSeatType(seatDto.getSeatType());
        if (seatDto.getPrice() > 0) seat.setPrice(seatDto.getPrice());

        seat.setAvailable(true);

        Seat updatedSeat = seatRepository.save(seat);
        return mapToDto(updatedSeat);
    }


    @Override
    public void deleteSeat(String seatNumber) {
        seatRepository.deleteBySeatNumber(seatNumber);
    }

    @Override
    public List<SeatDto> getSeatsByBus(String busNumber) {
        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(() -> new RuntimeException("Bus not found: " + busNumber));

        return seatRepository.findByBus(bus)
                .stream()
                .map(this::mapToDto)
                .toList();
    }


}

