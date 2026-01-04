package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.SeatDto;
import java.util.List;

public interface SeatService {
    List<SeatDto> getAllSeat();
    SeatDto createSeat(SeatDto seatDto);
    SeatDto updateSeat(String busNumber,String seatNumber, SeatDto seatDto);
    void deleteSeat(String seatNumber);
    List<SeatDto> getSeatsByBus(String busNumber);
}

