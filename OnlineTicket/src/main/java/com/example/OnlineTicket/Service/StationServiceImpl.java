package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.Repository.StationRepository;
import com.example.OnlineTicket.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public Station createStation(Station station) {
        return null;
    }

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public Station getStationById(Long id) {
        return stationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Station not found with ID: " + id));
    }

    @Override
    public void deleteStation(Long id) {
        stationRepository.deleteById(id);
    }
}

