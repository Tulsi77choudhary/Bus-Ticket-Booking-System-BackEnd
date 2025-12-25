package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.BusDTO;
import com.example.OnlineTicket.model.Bus;

import java.util.List;

public interface BusService {
    Bus addBus(Bus bus);

    List<Bus> searchBuses(String busNumber, String source, String destination);


    BusDTO updateBus(String busNumber, BusDTO busDTO);

    void deleteBus(Long id);

    BusDTO getBusById(Long id);
    //Optional<Seat> findByBusAndSeatNumber(Bus bus, String seatNumber);


    List<Bus> getAllBuses(String source, String destination, String busNumber);

    BusDTO mapToBusDto(Bus bus);

}
