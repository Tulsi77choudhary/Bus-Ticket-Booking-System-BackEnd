package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.RoutesDTO;
import com.example.OnlineTicket.Repository.BusRepository;
import com.example.OnlineTicket.Repository.RoutesRepository;
import com.example.OnlineTicket.model.Bus;
import com.example.OnlineTicket.model.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoutesServiceImpl implements RoutesService {

    @Autowired
    private RoutesRepository routesRepository;

    @Autowired
    private BusRepository busRepository;

    @Override
    public RoutesDTO createRoute(RoutesDTO dto) {
        Routes route = toEntity(dto);
        Routes saved = routesRepository.save(route);
        return toDto(saved);
    }

    @Override
    public List<RoutesDTO> getAllRoutes() {
        return routesRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RoutesDTO getRouteById(Long id) {
        Routes route = routesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + id));
        return toDto(route);
    }

    @Override
    public void deleteRoute(Long id) {
        routesRepository.deleteById(id);
    }

    @Override
    public RoutesDTO updateRoute(Long id, RoutesDTO dto) {
        Routes existing = routesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + id));

        Bus bus = busRepository.findByBusNumber(dto.getBusNumber())
                .orElseThrow(() -> new RuntimeException("Bus not found with number: " + dto.getBusNumber()));

        existing.setBus(bus);
        existing.setSource(dto.getSource());
        existing.setDestination(dto.getDestination());
        existing.setDate(dto.getDate());
        existing.setTime(dto.getTime());
        existing.setCost(dto.getCost());



        Routes updated = routesRepository.save(existing);
        return toDto(updated);
    }

    private RoutesDTO toDto(Routes route) {
        RoutesDTO dto = new RoutesDTO();
        dto.setId(route.getId());
        dto.setBusNumber(route.getBus().getBusNumber());
        dto.setSource(route.getSource());
        dto.setDestination(route.getDestination());
        dto.setDate(route.getDate());
        dto.setTime(route.getTime());
        dto.setCost(route.getCost());
        return dto;
    }



    private Routes toEntity(RoutesDTO dto) {
        Bus bus = busRepository.findByBusNumber(dto.getBusNumber())
                .orElseThrow(() -> new RuntimeException("Bus not found with number: " + dto.getBusNumber()));

        Routes route = new Routes();
        route.setId(dto.getId());
        route.setBus(bus);
        route.setSource(dto.getSource());
        route.setDestination(dto.getDestination());
        route.setDate(dto.getDate());
        route.setTime(dto.getTime());
        route.setCost(dto.getCost());
        return route;
    }


}
