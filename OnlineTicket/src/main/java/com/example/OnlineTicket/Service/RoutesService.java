package com.example.OnlineTicket.Service;

import com.example.OnlineTicket.DTO.RoutesDTO;
import java.util.List;

public interface RoutesService {
    RoutesDTO createRoute(RoutesDTO dto);
    List<RoutesDTO> getAllRoutes();
    RoutesDTO getRouteById(Long id);
    void deleteRoute(Long id);
    RoutesDTO updateRoute(Long id, RoutesDTO dto);

}

