package com.geektrust.backend.entities;

import com.geektrust.backend.exceptions.InvalidAmountException;
import com.geektrust.backend.exceptions.InvalidPassengerException;
import java.util.ArrayList;


public class Station implements Comparable<Station>
{
   private String id;
   private final String name; 
   private final ArrayList<Passenger> passengers;
   private int travelfareCollection;
   private int discount;
   private int serviceFee;
   private static final int MIN_AMOUNT = 0;

   public Station(String id,String name)
   {
    this(name); 
    this.id = id;
   }

   public Station(String name)
   {
    this.name = name;
    this.passengers = new ArrayList<Passenger>();                       
    this.travelfareCollection = 0;
    this.discount = 0;
    this.serviceFee = 0;
   }
    
    public String getId() 
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }
    
    public  ArrayList<Passenger> getPassengers()
    {
        return  this.passengers;
    }
    
    public int getTravelFareCollection()
    {
        return  this.travelfareCollection;
    }
                                          
    public int getTotalDiscount()
    {
        return  this.discount;
    }
                                                           
    public int getTotalServiceFee()             
    {
        return  this.serviceFee;
    }   

    public int getTotalCollection()
    {
        return  this.travelfareCollection +  this.serviceFee;
    }

    public void addTravelFare(int travelFare)
    {
        validateAmount(travelFare);
        this.travelfareCollection += travelFare;
    }  
    
    public void addServiceFee(int serviceFee)
    {
        validateAmount(serviceFee);
        this.serviceFee += serviceFee;
    }  

    public void addDiscount(int discount)
    {
        validateAmount(discount);
        this.discount += discount;
    }  

    public void addPassenger(Passenger passenger)
    {
        validatePassenger(passenger);
        this.passengers.add(passenger);
    }



    private void validateAmount(int amount)
    {
       if(amount<=MIN_AMOUNT)  throw new InvalidAmountException();
    }

    private void validatePassenger(Passenger passenger)
    {
       if(passenger == null) throw new InvalidPassengerException();
    }

    @Override
    public int compareTo(Station other) {
        return other.name.compareTo(this.name);
    }

    
   @Override
   public int hashCode() {
       final int prime = 31;
       int result = 1;
       result = prime * result + ((id == null) ? 0 : id.hashCode());
       result = prime * result + ((name == null) ? 0 : name.hashCode());
       return result;
   }

   @Override
   public boolean equals(Object obj) {
       if (this == obj)
           return true;
       if (obj == null)
           return false;
       if (getClass() != obj.getClass())
           return false;

       Station other = (Station) obj;
       if(this.name.equals(other.name))
           return true;
           
       return false;
   }

}
