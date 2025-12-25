package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.model.Station;

import java.util.List;

public interface StationService {
    Station createStation(Station station);
    List<Station> getAllStations();
    Station getStationById(Long id);
    void deleteStation(Long id);
}

