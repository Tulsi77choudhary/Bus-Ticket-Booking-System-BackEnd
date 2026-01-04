package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.BusDTO;
import com.example.OnlineTicket.DTO.BusResponseDTO;
import com.example.OnlineTicket.DTO.SeatDto;
import com.example.OnlineTicket.Excaption.ResourceNotFoundException;
import com.example.OnlineTicket.Repository.BusRepository;
import com.example.OnlineTicket.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BusServiceImpl implements BusService{
    @Autowired
    private BusRepository  busRepository;

    @Override
    public Bus addBus(BusDTO busDto) {

        if (busRepository.existsByBusNumber(busDto.getBusNumber())) {
            throw new RuntimeException("Bus with this number already exists");
        }

       Bus bus = new Bus();
       bus.setBusName(busDto.getBusName());
       bus.setBusNumber(busDto.getBusNumber());
       bus.setSource(busDto.getSource());
       bus.setDestination(busDto.getDestination());
       bus.setBusType(busDto.getBusType());
       bus.setTotalSeats(busDto.getTotalSeats());
       bus.setTime(busDto.getDepartureTime());

        return busRepository.save(bus);
    }

    @Override
    public List<Bus> searchBuses(String busNumber, String source, String destination) {
        busNumber = (busNumber != null && !busNumber.isBlank()) ? busNumber.trim() : null;
        source = (source != null && !source.isBlank()) ? source.trim() : null;
        destination = (destination != null && !destination.isBlank()) ? destination.trim() : null;

        return busRepository.searchBuses(busNumber,source,destination);
    }

    @Override
    public BusResponseDTO updateBus(String busNumber, BusDTO busDTO) {
        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Bus not found with number: " + busNumber));

        bus.setSource(busDTO.getSource());
        bus.setDestination(busDTO.getDestination());
        bus.setDate(busDTO.getDate());
        bus.setTime(busDTO.getDepartureTime());
        bus.setTotalSeats(busDTO.getTotalSeats());

        Bus updateBus = busRepository.save(bus);
        return mapToBusResponseDTO(updateBus);
    }

    public BusResponseDTO mapToBusResponseDTO(Bus bus) {

        List<SeatDto> seatDto = bus.getSeats().stream().map(seat -> {
            SeatDto s = new SeatDto();
            s.setPrice(seat.getPrice());
            s.setBusNumber(seat.getBus().getBusNumber());
            return s;
        }).toList();

        BusResponseDTO response = new BusResponseDTO();
        response.setBusNumber(bus.getBusNumber());
        response.setSource(bus.getSource());
        response.setDestination(bus.getDestination());
        response.setDate(bus.getDate());
        response.setDepartureTime(bus.getTime());
        response.setTotalSeats(bus.getTotalSeats());
        response.setSeats(seatDto);

        return response;
    }

    @Override
    public List<Bus> getAllBuses() {
        return busRepository.findAll();

    }

//    @Override
//    public BusDTO updateBus(String busNumber, BusDTO busDTO) {
//        Bus existingBus = busRepository.findByBusNumber(busNumber)
//                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + busNumber));
//
//
//        existingBus.setSource(busDTO.getSource());
//        existingBus.setDestination(busDTO.getDestination());
//        existingBus.setBusNumber(busDTO.getBusNumber());
//        existingBus.setDate(busDTO.getDate());
//        existingBus.setTime(busDTO.getTime());
//        existingBus.setTotalSeats(busDTO.getTotalSeats());
//
//        Bus updatedBus = busRepository.save(existingBus);
//        return toDto(updatedBus);
//    }

    private BusDTO toDto(Bus bus) {
        BusDTO dto = new BusDTO();
        dto.setBusNumber(bus.getBusNumber());
        dto.setSource(bus.getSource());
        dto.setDestination(bus.getDestination());
        dto.setDate(bus.getDate());
        dto.setDepartureTime(bus.getTime());
        dto.setTotalSeats(bus.getTotalSeats());
        return dto;
    }

    @Override
    public void deleteBus(Long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Bus not found with ID:"));
        busRepository.delete(bus);
    }
    @Override
    public BusResponseDTO getBusById(Long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + id));
        return mapToBusResponseDTO(bus);
    }

    @Override
    public List<Bus> getAllBuses(String source, String destination, String busNumber) {


        if (busNumber != null && !busNumber.isBlank()) {
            return busRepository.findByBusNumber(busNumber)
                    .map(List::of)
                    .orElse(Collections.emptyList());
        }

        if (source != null && !source.isBlank() && destination != null && !destination.isBlank()) {
            return busRepository.findBySourceAndDestination(source, destination);
        }

        return busRepository.findAll();
    }



}
