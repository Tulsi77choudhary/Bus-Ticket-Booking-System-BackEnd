package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.BusDTO;
import com.example.OnlineTicket.DTO.SeatDto;
import com.example.OnlineTicket.Repository.BusRepository;
import com.example.OnlineTicket.model.Bus;
import com.example.OnlineTicket.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BusServiceImpl implements BusService{
    @Autowired
    private BusRepository  busRepository;
    @Override
    public Bus addBus(Bus bus) {
        List<Seat> seats = new ArrayList<>();
        String[] rows = {"A", "B", "C"};
        int seatsPerRow = 10;

        for (String row : rows) {
            for (int i = 1; i <= seatsPerRow; i++) {
                Seat seat = new Seat();
                seat.setSeatNumber(row + i);
                seat.setPrice(800);
                seat.setBus(bus);
                seats.add(seat);
            }
        }

        bus.setSeats(seats);
        bus.setTotalSeats(seats.size());

        return busRepository.save(bus);
    }
    public BusDTO mapToBusDto(Bus bus) {
        BusDTO dto = new BusDTO();
        dto.setId(bus.getId());
        dto.setBusNumber(bus.getBusNumber());
        dto.setSource(bus.getSource());
        dto.setDestination(bus.getDestination());
        dto.setDate(bus.getDate());
        dto.setTime(bus.getTime());
        dto.setTotalSeats(bus.getTotalSeats());

        List<SeatDto> seatDtos = bus.getSeats().stream().map(seat -> {
            SeatDto s = new SeatDto();
            s.setSeatNumber(seat.getSeatNumber());
            s.setPrice(seat.getPrice());
            return s;
        }).toList();

        dto.setSeats(seatDtos);
        return dto;
    }

    @Override
    public List<Bus> searchBuses(String busNumber, String source, String destination) {
        if (busNumber != null && !busNumber.isEmpty()) {
            return busRepository.findByBusNumber(busNumber)
                    .map(List::of)
                    .orElse(List.of());
        } else if (source != null && destination != null) {
            return busRepository.findBySourceAndDestination(source, destination);
        } else if (source != null) {
            return busRepository.findBySource(source);
        } else if (destination != null) {
            return busRepository.findByDestination(destination);
        } else {
            return busRepository.findAll();
        }
    }
    @Override
    public BusDTO updateBus(String busNumber, BusDTO busDTO) {
        Bus existingBus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + busNumber));

        if (!existingBus.getBusNumber().equals(busDTO.getBusNumber())) {
            boolean exists = busRepository.findByBusNumber(busDTO.getBusNumber()).isPresent();
            if (exists) {
                throw new RuntimeException("Bus number already exists: " + busDTO.getBusNumber());
            }
        }
        existingBus.setSource(busDTO.getSource());
        existingBus.setDestination(busDTO.getDestination());
        existingBus.setBusNumber(busDTO.getBusNumber());
        existingBus.setDate(busDTO.getDate());
        existingBus.setTime(busDTO.getTime());
        existingBus.setTotalSeats(busDTO.getTotalSeats());

        Bus updatedBus = busRepository.save(existingBus);
        return toDto(updatedBus);
    }

    private BusDTO toDto(Bus bus) {
        BusDTO dto = new BusDTO();
        dto.setId(bus.getId());
        dto.setBusNumber(bus.getBusNumber());
        dto.setSource(bus.getSource());
        dto.setDestination(bus.getDestination());
        dto.setDate(bus.getDate());
        dto.setTime(bus.getTime());
        dto.setTotalSeats(bus.getTotalSeats());
        return dto;
    }


    @Override
    public void deleteBus(Long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Bus not found with ID:"));
        busRepository.deleteById(id);
    }
    @Override
    public BusDTO getBusById(Long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + id));
        return mapToBusDto(bus);
    }

    @Override
    public List<Bus> getAllBuses(String source, String destination, String busNumber) {
        if (source != null && destination != null && busNumber != null) {
            return busRepository.findBySourceAndDestinationAndBusNumber(source, destination, busNumber);
        } else if (source != null && destination != null) {
            return busRepository.findBySourceAndDestination(source, destination);
        } else if (busNumber != null) {
            return busRepository.findByBusNumber(busNumber)
                    .map(List::of)
                    .orElse(List.of());
        }
        return busRepository.findAll();
    }


}
