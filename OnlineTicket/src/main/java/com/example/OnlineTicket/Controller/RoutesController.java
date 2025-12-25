package com.example.OnlineTicket.Controller;

import com.example.OnlineTicket.DTO.RoutesDTO;
import com.example.OnlineTicket.Service.RoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routes")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class RoutesController {

    @Autowired
    private RoutesService routesService;

    @PostMapping
    public ResponseEntity<RoutesDTO> createRoute( @RequestBody RoutesDTO dto) {
        return ResponseEntity.ok(routesService.createRoute(dto));
    }

    @GetMapping
    public ResponseEntity<List<RoutesDTO>> getAllRoutes() {
        return ResponseEntity.ok(routesService.getAllRoutes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoutesDTO> getRouteById(@PathVariable Long id) {
        return ResponseEntity.ok(routesService.getRouteById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoutesDTO> updateRoute(@PathVariable Long id, @RequestBody RoutesDTO dto) {
        return ResponseEntity.ok(routesService.updateRoute(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoute(@PathVariable Long id) {
        routesService.deleteRoute(id);
        return ResponseEntity.noContent().build();
    }
}


