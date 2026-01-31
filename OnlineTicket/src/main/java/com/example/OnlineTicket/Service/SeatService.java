package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.SeatDto;

import java.util.List;

public interface SeatService {
    List<SeatDto> getAllSeats();
    SeatDto createSeat(SeatDto request);
    SeatDto updateSeat(String busNumber, String seatNumber, SeatDto seatDto);

    void deleteSeat(String busNumber,String seatNumber);

    List<SeatDto> getSeatsByBus(String busNumber);
}

