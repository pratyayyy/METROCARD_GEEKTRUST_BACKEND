package com.geektrust.backend.entities;

import com.geektrust.backend.exceptions.InvalidAmountException;

public class MetroCard {
    private String id; 
    private final String cardNo;
    private int balance;
    private final int MIN_AMOUNT=0;

    public MetroCard(String id, String cardNo, int balance)
    {
           this(cardNo,balance);
           this.id = id;
    }

    public MetroCard(String cardNo, int balance)
    {
           this.cardNo = cardNo;
           this.balance = balance;
    }


    public String getId() 
    {
      return this.id;
    }

    public String getCardNumber()
    {
        return cardNo;
    }

    public int getBalance()
    {
        return balance;
    }

    public void addAmount(int amount)
    {
      validateAmountCredit(amount);
      this.balance += amount;
    }

    public void deductAmount(int amount)
    {
      validateAmountCredit(amount);
      this.balance -= amount;
    }

    public void validateAmountCredit(int amount)
    { 
      if(amount<MIN_AMOUNT) throw new InvalidAmountException();
    }

    
    public void validateAmountDedit(int amount)
    { 
      if(amount<MIN_AMOUNT || amount>balance) throw new InvalidAmountException();
    }

    public boolean checkSufficientBalance(int amount)
    {
      if (amount <= this.balance)
            return true;
        else
            return false;
    } 

    
    @Override
    public boolean equals(Object obj)
    {
         if(this==obj) return true;
         if(obj==null) return false;
         if(this.getClass()!=obj.getClass()) return false;

         MetroCard m = (MetroCard)obj;
         if((this.cardNo.equals(m.cardNo)) && (this.balance == m.balance))
         {
          return true;
         }
         return false;
     
    }

    @Override
    public int hashCode()
     {
        final int prime = 31;
        int result = 1;
        result = prime * result + balance;
        result = prime * result + ((cardNo == null) ? 0 : cardNo.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    
}
