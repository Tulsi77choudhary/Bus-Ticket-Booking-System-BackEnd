package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.BusRequest;
import com.example.OnlineTicket.DTO.CreateBus;
import com.example.OnlineTicket.DTO.BusResponseDTO;
import com.example.OnlineTicket.Excaption.BusException;
import com.example.OnlineTicket.model.Bus;
import jakarta.transaction.Transactional;

import java.util.List;

public interface BusService {
    Bus createBus(CreateBus request) throws RuntimeException;
    List<Bus> searchBuses(BusRequest request) throws BusException;
    BusResponseDTO updateBus(String busNumber, CreateBus request);

    List<Bus> getAllBuses();

    void deleteBus(String busNumber);

    @Transactional
    void cancelBus(Long busId);
}
