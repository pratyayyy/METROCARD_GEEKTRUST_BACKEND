package com.geektrust.backend.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.geektrust.backend.entities.Station;

public class StationRepository implements IStationRepository {
    private final HashMap<String, Station> stationMap;
    int counter = 0;

    public StationRepository(HashMap<String, Station> stationMap)
    {
       this.stationMap = stationMap;
       this.counter = stationMap.size();
    }

    public StationRepository()
    {
        this.stationMap=new HashMap<String,Station>();
    }

    @Override
    public Station save(Station stationentity)
    {
        if(stationentity.getId() == null)
        {
            counter++;
            Station station = new Station(Integer.toString(counter),stationentity.getName());
            stationMap.put(station.getId(), station);
            return station;
        }
        stationMap.put(stationentity.getId(),stationentity);
        return stationentity;
    }

    @Override
    public Optional<Station> findByStationName(String name)
    {
        Optional<Station> station = stationMap.values().stream().filter(s->s.getName().equals(name)).findFirst();
        return station;
    }

    @Override
    public List<Station> findAll()
    {
        List<Station> stationList = stationMap.values().stream().collect(Collectors.toList());
        return stationList;
    }

}
