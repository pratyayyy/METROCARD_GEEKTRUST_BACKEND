package com.geektrust.backend.dto;


import com.geektrust.backend.entities.PassengerType;

public class PassengerTypeSummary implements Comparable<PassengerTypeSummary> 
{
    private final PassengerType passengerType;
    private final int count;

    public PassengerTypeSummary(PassengerType passengerType, int count) 
    {
        this.passengerType = passengerType;
        this.count = count;
    }

    public PassengerType getPassengerType() {
        return this.passengerType;
    }

    public int getCount() {
        return this.count;
    }

    @Override
    public int compareTo(PassengerTypeSummary ob) {
        if(this.count < ob.count)
            return 1;
        else if(this.count == ob.count) 
        {
            return this.passengerType.compareTo(ob.passengerType);
        }
        else
        {
            return -1;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + count;
        result = prime * result + ((passengerType == null) ? 0 : passengerType.hashCode());
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

        PassengerTypeSummary ob = (PassengerTypeSummary) obj;
        if((this.passengerType.equals(ob.passengerType)) && (this.count == ob.count))
            return true;
        
        return false;
    }

}
