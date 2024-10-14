package com.geektrust.backend.entities;

public enum TravelFare {
    KID(50), ADULT(200), SENIOR_CITIZEN(100);

    private int travelfare;

    TravelFare(int travelfare)
    {
        this.travelfare = travelfare;
    }

    public int getFare()
    {
        return this.travelfare;
    }

}
