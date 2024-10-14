package com.geektrust.backend.dto;

import java.util.List;

public class PassengerSummary 
{

    private final List<PassengerTypeSummary> passengerTypeSummary;

    public PassengerSummary(List<PassengerTypeSummary> passengerTypeSummary) {
        this.passengerTypeSummary = passengerTypeSummary;
    }

    public List<PassengerTypeSummary> getPassengerTypeSummary() {
        return this.passengerTypeSummary;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((passengerTypeSummary == null) ? 0 :passengerTypeSummary.hashCode());    
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;

        PassengerSummary ob = (PassengerSummary) obj;
        if (this.passengerTypeSummary.equals(ob.passengerTypeSummary))
            return true;

        return false;
    }



}