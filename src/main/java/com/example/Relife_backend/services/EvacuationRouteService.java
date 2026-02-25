package com.example.Relife_backend.services;


import com.example.Relife_backend.dto.EvacuationRouteDTO;

import java.util.List;

public interface EvacuationRouteService {

    EvacuationRouteDTO createRoute(EvacuationRouteDTO dto);

    List<EvacuationRouteDTO> getAllRoutes();

    EvacuationRouteDTO updateRoute(Long id, EvacuationRouteDTO dto);

    void deleteRoute(Long id);
}
