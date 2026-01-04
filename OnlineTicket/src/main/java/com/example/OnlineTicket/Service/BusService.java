package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.BusDTO;
import com.example.OnlineTicket.DTO.BusResponseDTO;
import com.example.OnlineTicket.model.Bus;

import java.util.List;

public interface BusService {
    Bus addBus(BusDTO busDto) throws RuntimeException;
    List<Bus> searchBuses(String busNumber, String source, String destination);
    BusResponseDTO updateBus(String busNumber, BusDTO busDTO);
    void deleteBus(Long id);
    BusResponseDTO getBusById(Long id);

    List<Bus> getAllBuses(String source, String destination, String busNumber);

    BusResponseDTO mapToBusResponseDTO(Bus bus);

    List<Bus> getAllBuses();
}
