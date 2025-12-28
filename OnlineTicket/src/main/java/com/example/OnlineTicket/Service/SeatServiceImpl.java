package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.SeatDto;
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
        dto.setSeatNumber(seat.getSeatNumber());
        if (seat.getSeatType() != null) {
            dto.setSeatType(seat.getSeatType().toUpperCase());
        } else {
            dto.setSeatType(null);
        }
        dto.setPrice(seat.getPrice());
        dto.setBusNumber(seat.getBus().getBusNumber());
        return dto;
    }


    private Seat mapToEntity(SeatDto dto) {
        Seat seat = new Seat();
        seat.setSeatNumber(dto.getSeatNumber());
        seat.setSeatType(dto.getSeatType().toUpperCase());
        Bus bus = busRepository.findByBusNumber(dto.getBusNumber())
                .orElseThrow(() -> new RuntimeException("Bus not found: " + dto.getBusNumber()));
        seat.setBus(bus);

        return seat;
    }

    @Override
    public List<SeatDto> getAllSeat() {
        return seatRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SeatDto createSeat(SeatDto seatDto) {
        Bus bus = busRepository.findByBusNumber(seatDto.getBusNumber())
                .orElseThrow(() -> new RuntimeException("Bus not found: " + seatDto.getBusNumber()));

        Optional<Seat> existing = seatRepository.findBySeatNumberAndBus(seatDto.getSeatNumber(), bus);
        if (existing.isPresent()) {
            throw new RuntimeException("Seat already exists for this bus");
        }

        Seat seat = new Seat();
        seat.setSeatNumber(seatDto.getSeatNumber());
        seat.setPrice(seat.getPrice());
        seat.setBus(bus);
        Seat saved = seatRepository.save(seat);

        return new SeatDto(
                saved.getSeatNumber(),
                saved.getSeatType(),
                saved.getPrice(),
                bus.getBusNumber()
        );
    }

    @Override
    public SeatDto toggleSeatAvailability(String seatNumber) {
        return null;
    }

    @Override
    public SeatDto updateSeat(String busNumber, String seatNumber, SeatDto seatDto) {
        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        Seat seat = seatRepository.findBySeatNumberAndBus(seatNumber,bus)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seatDto.getSeatType() != null){
            seat.setSeatType(seatDto.getSeatType().toUpperCase());
        }
        seat.setSeatNumber(seatDto.getSeatNumber());
        seat.setPrice(seatDto.getPrice());
        bus.setBusNumber(busNumber);
        Seat updated = seatRepository.save(seat);

        return new SeatDto(
                updated.getSeatNumber(),
                updated.getSeatType(),
                updated.getPrice(),
                updated.getBus().getBusNumber()
        );
    }

    @Override
    public void deleteSeat(String seatNumber) {
        seatRepository.deleteBySeatNumber(seatNumber);
    }

    @Override
    @Transactional
    public List<SeatDto> selectSeats(String busNumber, List<String> seatNumbers) {

        List<Seat> seats = seatRepository
                .findByBus_BusNumberAndSeatNumberIn(busNumber, seatNumbers);

        if (seats.isEmpty()) {
            throw new RuntimeException("Seats not found");
        }
        for (Seat seat : seats) {
            if (!seat.isAvailable()) {
                throw new RuntimeException(
                        "Seat already booked: " + seat.getSeatNumber()
                );
            }
            seat.setAvailable(false);
        }
        seatRepository.saveAll(seats);

        return seats.stream()
                .map(this::mapToDto)
                .toList();
    }


    @Override
    public List<SeatDto> getSeatsByBus(String busNumber) {
        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(()-> new RuntimeException("Bus not found"));

        return seatRepository.findByBus(bus)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

}

