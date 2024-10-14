package com.geektrust.backend.dto;

public class CollectionSummary {

    private final String stationName;
    private final int totalCollection;
    private final int discountCollection;

    
    public CollectionSummary(String stationName, int totalCollection, int discountCollection) 
    {
        this.stationName = stationName;
        this.totalCollection = totalCollection;
        this.discountCollection = discountCollection;
    }

    public String getStationName() {
        return this.stationName;
    }

    public int getTotalCollection() {
        return this.totalCollection;
    }

    public int getDiscountCollection() {
        return this.discountCollection;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + discountCollection;
        result = prime * result + ((stationName == null) ? 0 : stationName.hashCode());
        result = prime * result + totalCollection;
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

        CollectionSummary ob = (CollectionSummary) obj;
        if ((this.stationName.equals(ob.stationName)) && (this.totalCollection == ob.totalCollection) && (this.discountCollection == ob.discountCollection))
            return true;
    
        return false;
    }

}

    

