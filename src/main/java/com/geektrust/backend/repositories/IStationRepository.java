package com.geektrust.backend.repositories;

import java.util.List;
import java.util.Optional;
import com.geektrust.backend.entities.Station;

public interface IStationRepository {
    public Station save(Station stationentity);
    public Optional<Station> findByStationName(String name);
    public List<Station> findAll();
    
}
